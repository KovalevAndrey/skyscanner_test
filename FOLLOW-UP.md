# Implementation:

### Q) What Architecture did you choose and why?

Clean architecture. Keep the classes small and testable. Separate the layers.
Loosely coupled code makes it easy to add new features


### Q) What libraries did you add to the app? What are they used for?

OkHttp + Retrofit for networking. RxJava for asynchronous calls and combining the results. Kotlin <3.
Robolectric and mockito for tests. Dagger 2 for DI. Gson for converting JSON to pojo

---

# General:

### Q) How long did you spend on the test?

16 hours

### Q) If you had more time, what further improvements or new features would you add?

Test everything that is possible. Make interactor survive orientation changes. (Maybe look at Android architecture components).
UI tests. Support all the possible response codes

### Q) Which parts are you most proud of? And why?
Can't pick one

### Q) Which parts did you spend the most time with? What did you find most difficult?
Can't name one. More or less they're equally difficult

### Q) How did you find the test overall? If you have any suggestions on how we can improve the test or our API, we'd love to hear them.
Pretty interesting but I'd ease it in order to make a candidate spent less time.