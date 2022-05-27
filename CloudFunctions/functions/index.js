const functions = require("firebase-functions");

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//   functions.logger.info("Hello logs!", {structuredData: true});
//   response.send("Hello from Firebase!");
// });

const admin = require('firebase-admin');
admin.initializeApp();

exports.notifyNewMeal = functions.firestore.document('/Orders/{documentId}')
    .onCreate((snap, context) => {
      // Grab the current value of what was written to Firestore.
      const orderId = snap.id;
      const vendorId = snap.data().vendorId;
      const mealId = snap.data().mealId;


      // Access the parameter `{documentId}` with `context.params`
      functions.logger.log('Notifying about vendor ', vendorId,' and meal ', mealId);
      const topic = 'mealposts-'+vendorId;

      const message = {
        data: {
          vendorId: vendorId,
          mealId: mealId,
          orderId: orderId
        },
        topic: topic
      };
      return admin.messaging().send(message)
    });
