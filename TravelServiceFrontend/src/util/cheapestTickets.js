//https://api.travelpayouts.com/v1/prices/cheap?origin=MOW&destination=HKT&depart_date=2022-01-04&return_date=2022-01-08&page=1&currency=RUB
const cheapestTickets ={
    "success": true,
    "data": {
        "HKT": {
            "1": {
                "price": 72381,
                "airline": "KC",
                "flight_number": 876,
                "departure_at": "2022-01-04T22:20:00+03:00",
                "return_at": "2022-01-08T18:30:00+07:00",
                "expires_at": "2022-01-03T11:57:49Z"
            },
            "2": {
                "price": 57940,
                "airline": "5N",
                "flight_number": 237,
                "departure_at": "2022-01-04T00:25:00+03:00",
                "return_at": "2022-01-08T20:35:00+07:00",
                "expires_at": "2022-01-03T11:57:49Z"
            },
            "3": {
                "price": 148700,
                "airline": "TK",
                "flight_number": 420,
                "departure_at": "2022-01-04T07:15:00+03:00",
                "return_at": "2022-01-08T11:40:00+07:00",
                "expires_at": "2022-01-03T11:57:49Z"
            }
        }
    },
    "currency": "RUB"
}