# Programowanie aplikacji mobilnych i webowych

Repozytorium zawierające notatki z poszczególnych laboratoriów.

## Database diagram

![database diagram](images/db_diagram.png)


## Curl examples

### Get

```
curl -v localhost:8080/parcels -H 'Content-type:application/json'
```

### POST

```
curl -v -X POST localhost:8080/parcels -H 'Content-type:application/json' -d '{"name":"Parcel v1.0"}'
```

### PUT

```
curl -v -X PUT  localhost:8080/parcels/1 -H 'Content-type:application/json' -d '{"id":1, "name":"Parcel v1.1"}'
```

### DELETE

```
curl -v -X DELETE  localhost:8080/parcels/1 -H 'Content-type:application/json'
```
