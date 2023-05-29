## Agenda Voting

The propose of this application is to create and vote in Agendas

### This project was made using:

- Java 17.0.6;
- Spring boot 3.1.0 with Maven;
- MySQL 8.0.33;

### Endpoints

Feel free to use Swagger UI at http://localhost:8080/swagger-ui/index.html or the public Postman collection
at https://www.postman.com/bengelado/workspace/agenda-voting/collection/1797660-6784446b-87c1-4f90-af25-9a7bdf82f08f

In this project we have 4 endpoints:

GET /agenda - List all agendas created

Response example:

```JSON
[
    {
        "id": 1,
        "textField": "text",
        "numericField": 123,
        "dateField": "2023-05-23",
        "createdAt": "2023-05-28T11:36:04.838721",
        "secondsToExpire": 60
    }
]
```

---

POST /agenda/create - create an agenda

Request example:

```JSON
{
    "textField": "text",
    "numericField": 123,
    "dateField": "2023-05-23",
    "secondsToExpire": 120 //optional
}
```

Response example:

```JSON
{
    "id": 3,
    "textField": "text",
    "numericField": 123,
    "dateField": "2023-05-23",
    "createdAt": "2023-05-28T22:09:55.515324",
    "secondsToExpire": 120
}
```

---

POST /agenda/{agendaId}/vote - vote on an agenda

Request example:

```JSON
{
  "cpf": "218.242.587-04",//unique id
  "inFavor": false //'Sim'/'Não'
}
```

Response example:

```JSON
{
  "id": 5,
  "agenda": {
    "id": 1,
    "textField": "text",
    "numericField": 123,
    "dateField": "2023-05-23",
    "createdAt": "2023-05-28T11:36:04.838721",
    "secondsToExpire": 60
  },
  "cpf": "abc8",
  "inFavor": false
}
```

---

GET /agenda/{agendaId}/voting-result - get the voting result of an agenda

Response example:

```JSON
{
  "id": 1,
  "textField": "text",
  "numericField": 123,
  "dateField": "2023-05-23",
  "secondsToExpire": 60,
  "createdAt": "2023-05-28T11:36:04.838721",
  "votesTotal": 5,
  "votesYes": 3,
  "votesNo": 2
}
```

---

### Performance

For performance test Apache JMeter was used:

![Apache JMeter](https://cdn.discordapp.com/attachments/732598338863497357/1112543025567121500/jmeter_results.png "Apache JMeter")

![Apache JMeter](https://cdn.discordapp.com/attachments/732598338863497357/1112544145983144086/image.png "Apache JMeter")

![Apache JMeter](https://cdn.discordapp.com/attachments/732598338863497357/1112544343505518662/image.png "Apache JMeter")

![Apache JMeter](https://cdn.discordapp.com/attachments/732598338863497357/1112544551048056862/image.png "Apache JMeter")

As you can see running 4 times with 1000 Users in P99 worst case we get 76 ms

### Versioning of the API

The most common approach I guess is to simply add /v1, /v2, /v3... to the endpoints

Example:
http://www.example.com/api/v1/products

Or:
http://www.example.com/api/products?version=1

Change the version is might be necessary when the signature change, but it is also possible to do with logical changes.