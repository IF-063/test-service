## Required request/response headers
To load / send representations of resources use 'Content-Type' / 'Accept' headers.

## URL patterns
(All URL-addresses start with "/delay/{delay}/" where {delay} - is max value of random generated sleep-time. It means 
that the web-service will be sleeping [0; {delay}] seconds before doing some actions).

### Author
/delay/{delay}/authors
- GET - loads all authors (returns HTTP_STATUS.OK and requested resources)
- POST - saves new author (returns HTTP_STATUS.CREATED and 'Location' header with URI of saved author)
- DELETE - deletes all authors (returns HTTP_STATUS.NO_CONTENT)

/delay/{delay}/authors/{id}
- GET - loads author by id (returns HTTP_STATUS.OK and requested resources)
- POST - updates author by id (returns HTTP_STATUS.NO_CONTENT)
- DELETE - deletes the author (returns HTTP_STATUS.NO_CONTENT)

### Book
/delay/{delay}/books
- GET - loads all books (returns HTTP_STATUS.OK and requested resources)
- POST - saves new book (returns HTTP_STATUS.CREATED and 'Location' header with URI of saved book)
- DELETE - deletes all books (returns HTTP_STATUS.NO_CONTENT)

/delay/{delay}/books/{id}
- GET - loads book by id (returns HTTP_STATUS.OK and requested resources)
- POST - updates book by id (returns HTTP_STATUS.NO_CONTENT)
- DELETE - deletes the book (returns HTTP_STATUS.NO_CONTENT)
