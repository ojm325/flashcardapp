# flashcardapp
An Android flash card app that allows users to create a deck of flash cards for studying. Users can create a deck and then populate that deck with different types of cards. Currently, the types of cards that can be created are Question and Answer, Multiple Choice, Multiple Answer (just like Multiple Choice, but with checkboxes instead of radioboxes so that users can select more than one answer), and True or False.

# Future Plans
These are some of the features that I intend to implement in the future:
- Challenge Mode. Users will be able to select this option so that the app can track how many answers they guessed correct. This will probably be timed as well.
- Analytics. User's will be able to see their progress with each deck that they quiz themselves on. They may see such things as how often they answered correctly or incorrectly for each card, what responses they put down during each quiz, etc.
- Create decks and cards from a place other than the app. This will most likely be a website that users can go to where they can create an account and make the cards there.

# Libraries and Tools Used
- Butter Knife: For view injection. It makes the code behind setting up the Android views much cleaner and simpler. 
- DraggableFlipView(https://github.com/sasakicks/DraggableFlipView): Used while a user is quizzing themselves on a deck of cards. This is the code behind the cards flipping over to the other side to display the answer.
- Google Play Services - Drive: Allows for API control over a user's Google Drive account. This will most likely be deprecated in the future. The original purpose behind including this in the project was to have an alternative method to creating a deck of cards. I didn't want to necessarily make a full back-end approach for this, and originally thought that this approach would be feasible. However, due to how much I've expanded the original idea behind the app, using Drive is becoming less and less of a useful approach for tackling the problem of users wanting to create cards from a place outside of their mobile device.
- GSON: A library by Google that allows for JSON to be converted to a Java object and back. I have yet to use this library, but this will be useful if and when I create a back-end solution for this app.
