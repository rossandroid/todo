#TODO 
#### Android Application that implements MVP design pattern

High level Requirements:

* Display list of Todos
*  Create a Todo
*  Update a Todo
*  Delete a Todo.

All of the operations are available via REST API call.

### CRUD:
*  Create: POST to BASE_URL + /todos.json
*  Read: GET to BASE_URL + /todos.json
*  Update: PATCH to BASE_URL + /todos/{id}.json
*  Get By Id: GET to BASE_URL/todos/{id}.json
*  Delete: DELETE with BASE_URL + /todos/{id}.json
 
### Detailed Requirements:
* Display list of Todos by reading Todos from https://bboxx-android-coding-challenge.herokuapp.com/todos
*  Show only name, status, expiry_date and description.
*  ~~Add filter functionality by expiry_date [last 7 days, 1 month, 6 months], status, name~~ 
* Create functionality for creating/ changing status/ updating/ deleting Todos from the list by using POST, PUT, DELETE to the API.
*  Add a delete button when user swipes right.
*  When user completes a Todo status should be changed to complete.
*  Create pagination in the Todos list with page containing 5 Todos.

### Technical Stack:
* Kotlin
* SQLite for local database 
* Material Design
* Reactive programming
