# Summary for sprint 10

## Badis
I added the pickup location to the meal when placing the meal (the option to select another location other than the current one isn't implemented yet). I also worked on filtering through the meals that are fetched from the database (the fetching was implemented by Khalil) by location. I had issues with the build that are related to the location fetching.

## Elior (scrum master)
I implemented the upload of the meal to the database and cleaned the way the Allergen were handled. I had issues with image and vendorId management.
Some interdependencies between our tasks prevented me to do some tasks (but I could have used fake data anyway!)
## Khalil
I implemented fetching the meal list from the database. This task did not take me a lot of time, so I started working on some other aspects of the app that I felt were missing,
so I implemented saving the changes that were done in the profile editing page on the database, as well as registering a user when he first loggs in to the app 
(while showing a different display message according to your registration status). After that, I had to adjust the tests so that the build works, but I had an issue with
our lovely and capricious Jacoco that showed 0% coverage (even when running locally) even when the tests are running and passing. so I also worked on fixing that.
## Raed 

## Yassine 
## Overall team
We still get to work too late in the week, with late PR and not enough time for good code review.
The database is being more and more used and the overall codebase starts to be more coherent!
We have problems regarding the schedule of meetings, which leads to late meetings with few time to react afterwards.