# Summary for sprint 10

## Badis
## Elior (scrum master)
I implemented the upload of the meal to the database and cleaned the way the Allergen were handled. I had issues with image and vendorId management.
Some interdependencies between our tasks prevented me to do some tasks (but I could have used fake data anyway!)
## Khalil
I implemented fetching the meal list from the database. This task did not take me a lot of time, so I started working on some other aspects of the app that I felt were missing,
so I implemented saving the changes that were done in the profile editing page on the database, as well as registering a user when he first loggs in to the app 
(while showing a different display message according to your registration status). After that, I had to adjust the tests so that the build works, but I had an issue with
our lovely and capricious Jacoco that showed 0% coverage (even when running locally) even when the tests are running and passing. so I also worked on fixing that.
## Raed 
I implemeted fetching only the orders that a buyer made from the order database to be displayed in the past orders activity and fetched meal details in the detailed order activity.
This week's task was too long than usual as I had to change many things in the past code to make it coherent with the database implementation and I had a hard time with that(The content of the Order class, the activites, the adapter, tests...)
and I had some difficulties with the implementation in itself as I wanted to make it as clean as possible to avoid recoding it later but I had some roadblocks that I had to find a way to fix them( orders adapter not compatible with multiple objects etc..)
I didn't test the database implementation since khalil was working on that this week.
## Yassine
I finished implementing the chatting feature and its integration into the firestore. Now you can see the conversations that are fetched from the database and sending a message will add it to the db. 
## Overall team
We still get to work too late in the week, with late PR and not enough time for good code review.
The database is being more and more used and the overall codebase starts to be more coherent!
We have problems regarding the schedule of meetings, which leads to late meetings with few time to react afterwards.
