# library-demo
This project contains a REST API Spring backend and an initial client side React application located in `src.main/resources/static/js`. While not ideal to embed the React client in the Spring application it helps for Heroku deployments. Heroku can deploy Spring applications by looking for all front end resources in the `src.main/resources/static/` ...In a real environment, the client and server are both set up in different repositories and have separate deployments, but this setup shortcuts that process for demo purposes. 

## API Endpoints
- `localhost:8080/api/books` will access the collection of books
- `localhost:8080/api/books/{id}` will access a singular book by plugging in a numerical value for id
- `localhost:8080/api/books/{id}/add-hashtag` will provide a `POST` request to add a hashtag to a book
- `localhost:8080/api/campuses` will access the collection of campuses
- `localhost:8080/api/campuses/{campuseId}` will access a singular campus by plugging in a numerical value for campusId
- `localhost:8080/api/hashtags` will access the collection of hashtags
- `localhost:8080/api/add-hashtag` will provide a `POST` request to add a hashtag to the database, NOT attaching it to a book 
- `localhost:8080/api/hashtags/{id}/delete-hashtag` will provide a `DELETE` request to remove a hashtag 

## API Notes
- Initial data is seeded through `Populator.java`
- This application runs an in memory H2 database, therefore any interactions with the database happen locally and any `POST`, `DELETE` requests will be reset if the Spring server is shut down and restarted
- One regret is the inconsistencies in `hashtag` versus `hashTag` in several references throughout the application please be careful of how the backend is referencing this variable when interacting in the front end 

## React Client Notes
The following dependencies have been installed in the React application
- `axios` to handle promises, asynchronous calls, any CRUD operations on the API
- `react-css-modules` to provide unique style sheets for each component (does not matter if classes have same names in other files there will be no conflicts...this is great in a team setting so that members can work on features and not worry about their css selectors interfering with other parts of the application)
- `react-router-dom` handles the front end routing architecture and custom url's that the client will request 
- `jest` comes prepackaged in React applications as its testing suite
- `eslint-plugin-react` will provide warnings on code practices that could hang up deployments ... for example any references to unused variables in the front end will not allow for the application to deploy properly...Es Lint will catch these issues and warn the developer. 
