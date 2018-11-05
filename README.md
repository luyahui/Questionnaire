# Questionnaire

This project is used to build RESTful APIs for some questions.

Here is the useages for thoese APIs.

1. Get a question by ID:(HTTP GET) /question/{id}
2. Get a random question: (HTTP GET) /question/random?uuid=
3. Get all qeustions (pageNo and pageSize optional): (HTTP GET) /question/all
4. Add a new question: (HTTP POST) /question/add
5. Edit an exisiting question: (HTTP PUT) /question/edit
6. Delete an exisiting question: (HTTP DELETE) /question/delete/{id}
7. Answer a question: (HTTP POST) /record/answer/{qid}
