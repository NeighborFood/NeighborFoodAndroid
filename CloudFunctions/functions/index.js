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

exports.notifyNewMeal = functions.firestore.document('/Meals/{documentId}')
    .onCreate((snap, context) => {
      // Grab the current value of what was written to Firestore.
      const mealId = snap.id;
      const vendorID = snap.data().vendorID;


      // Access the parameter `{documentId}` with `context.params`
      functions.logger.log('Notifying about vendor ', vendorID,' and meal ', mealId);
      const topic = 'mealposts-'+vendorID;

      const message = {
        data: {
          vendorID: vendorID,
          mealId: mealId
        },
        topic: topic
      };
      return admin.messaging().send(message)
    });
