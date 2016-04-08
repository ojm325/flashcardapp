# flashcardapp
Updated: 04/08/16
This is an Android flashcard app that allows users to create a deck of flash cards for studying. The idea behind the app was to create a robust flashcard app that gives users a decent amount of options to determine how they prefer to study the content on their flashcards. Users can create a deck and then populate that deck with different types of cards. Currently, the types of cards that can be created are Question and Answer, Multiple Choice, Multiple Answer (just like Multiple Choice, but with checkboxes instead of radioboxes so that users can select more than one answer), and True or False.


## How It Works
The app uses DeckListActivity as the first class that it will use when opening the app, so that the first thing that the user gets introduced to is the ability to see all of the available decks and to be able to create a deck immediately by pressing the floating action button. 

If a user creates a deck, they are taken to the activity_create_deck view, which is handled by the CreateDeckActivity class. The CreateDeckActivity class communicated with the DataStorage to input the user's created deck into the appropriate storage. 

After the user has created a deck, they're taken to a view similar to the one presented at the main screen of the app, but this time this is the list view of all of the cards that are in this deck. This is handled by the CardListActivity. Clicking on the floating action button in this screen will allow for the user to create a card.

In the card creation screen (handled by the CreateCardActivity class), a user can put in the question of the card, the type of responses the card can take in, the choices/answer for the question, and notes. When a user selects a new card type, the section below the card type allows for the user to put in the choices/answer for the question. That section is handled through fragments so that if new card types get added in, we can create a new fragment layout and handle the functionality for that specific fragment alone rather than messing around with the entirety of the view. There are currently two classes that handle those fragments: CreateCardTypeMultipleFragment (for the fragment_create_card_type_multiple.xml layout) and CreateCardTypeWriteInFragment (for fragment_create_card_type_writein.xml). Once the user creates the card, they are taken back to the card list screen for that deck.

Because I wanted to have a central place to handle the actions of a deck (such as allowing a user to quiz themselves with a deck, modify their deck, delete their deck, etc.), pressing a deck in the main view of the app will take them to the deck options screen. Originally, I wanted to add in functionality to each row of the deck list that when a user swipes left or right on a row, a list of options slide out within that row. The major problem I encountered with that idea is that it would be very easy for the UI to get cluttered with icons. That idea is only practical if we want to give users two or less options (and right now, there's 5 options in the deck options screen).

Currently, if a user wants to quiz themselves with a deck of cards, the only option they have is the practice mode. This mode ignores any answer inputs made by the user, but it still displays answer in the back of the card. The functionality of the quiz is handled by the QuizTakingActivity class. The class also handles swiping gestures so that the user can navigate through the cards by swiping up or swiping down to show the next/previous card. 

The way that the app handles decks and cards is that the user can create a deck, which will get populated by cards. Cards would be inserted into a deck through an ArrayList of FlashCards. The FlashCards class is an interface that is implemented by the FlashCardMultipleChoice and FlashCardQuestionAndAnswer classes. FlashCardMultipleChoice handles flashcards that contain multiple answer choices. For the current state of the app, the card types that use this class are Multiple Choice, Multiple Answers, and True or False. The only type that uses FlashCardQuestionAndAnswer are Question and Answer card types. That card type takes in only one answer and has no answer choices. With this architecture, we allow for any type of flashcard to be included into the deck, as well as allow for the creation of new flashcard types that may not be suitable for either of the current flashcard classes.

For all of the activities in this app, BaseActivity exists as a central hub to handle global activity actions. Activities implemented in this project extend from BaseActivity, which extends from AppCompatActivity.


## Storage Setup
Currently, the app relies on SQLite for it's storage. This makes sense since we're dealing with relations between decks and cards, as well as the values associated with those objects. It also helps for scalability of this app so that we can add on more relations (like deck analytics) to the core part of the app. I am also working on an alternative approach to handling storage and the creation of decks/cards for users who feel that creating numerous cards on a mobile device may be too cumbersome.

The app is mainly using the following tables: card, deck, and card_choices. While the card and deck tables are self-explanatory, the card_choices table is a separate table that contains all of the answer choices for each card. It relies on the deck id and card id to make sure that the answers stay within the card its intended for. Having the answers in the same table as card would be annoying to manage (not scalable, not normalized). The tables "card_analytics" and "deck_analytics" have been implemented, but they're not being used at the moment. When the app is more fleshed out or in a more stable position, I'll focus on taking advantage of these tables.

All of the code to handle storage is under the Storage folder. I have an SQLiteHelper class to create the SQLite tables and to establish the naming of all the tables and rows. To handle the insertion, deletion, and modification of data, the DataStorage interface was made so that we can use the same methods for both SQLite storage (handled in the SQLiteDeckCardStorage class) and the online storage that I'll be implementing in the future.

For handling the storage of analytics, it follows a similar  structure to that of the one above. However, the AnalyticsDataStorage interface is only being used by SQLiteAnalyticsStorage, and the functionality of handling the analytics data has yet to be implemented.


## Libraries and Tools Used
- Butter Knife: For view injection. It makes the code behind setting up the Android views much cleaner and simpler. 
- Recycler View: It's a flexible view library that's a part of Google's support libary that allows us to render the deck list view. 
- GSON: A library by Google that allows for JSON to be converted to a Java object and back. I have yet to use this library, but this will be useful if and when I create a back-end solution for this app.


## Future Plans
These are some of the features that I intend to implement in the future:
- Challenge Mode. Users will be able to select this option so that the app can track how many answers they guessed correct. This will probably be timed as well.
- Analytics. User's will be able to see their progress with each deck that they quiz themselves on. They may see such things as how often they answered correctly or incorrectly for each card, what responses they put down during each quiz, etc.
- Create decks and cards from a place other than the app. This will most likely be a website that users can go to where they can create an account and make the cards there.


## Issues/Bugs To Keep in Mind
- Quizzing yourself with a deck of cards no longer works. Reason being is that I'm in the process of taking out the previous library that I used to handle the functionality of "flipping" your card over to the other side during quizzing, and replacing it with code made specifically for the app.
- When you create a card, the app will verify if the inputs the user has put in makes sense to put into storage. However, the verifier is still not fully fleshed out. There's a chance that putting in bad data in the card creation screen can mess up how data is retrieved from the SQLite database.