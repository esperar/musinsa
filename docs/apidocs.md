## API Docs
- base-endpoint: `${host:localhost}:${port:8080}/clothing`

### 1. 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API

#### Request

- HTTP METHOD: GET
- EndPoint: /api1

#### Response

- Status: 200

```json
{
    "categoryClothing": [
        {
            "category": "TOP",
            "brand": "C",
            "price": 10000
        },
        {
            "category": "OUTER",
            "brand": "E",
            "price": 5000
        },
        {
            "category": "PANTS",
            "brand": "D",
            "price": 3000
        },
        {
            "category": "SNEAKERS",
            "brand": "A",
            "price": 9000
        },
        {
            "category": "SNEAKERS",
            "brand": "G",
            "price": 9000
        },
        {
            "category": "BAG",
            "brand": "A",
            "price": 2000
        },
        {
            "category": "HAT",
            "brand": "D",
            "price": 1500
        },
        {
            "category": "SOCKS",
            "brand": "I",
            "price": 1700
        },
        {
            "category": "ACCESSORY",
            "brand": "F",
            "price": 1900
        }
    ],
    "totalPrice": 43100
}
```

<br>

### 2. 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API

#### Request

- HTTP METHOD: GET
- EndPoint: /api2

#### Response

- Status: 200

```json
{
  "brand": "D",
  "clothing": [
    {
      "category": "TOP",
      "price": 10100
    },
    {
      "category": "OUTER",
      "price": 5100
    },
    {
      "category": "PANTS",
      "price": 3000
    },
    {
      "category": "SNEAKERS",
      "price": 9500
    },
    {
      "category": "BAG",
      "price": 2500
    },
    {
      "category": "HAT",
      "price": 1500
    },
    {
      "category": "SOCKS",
      "price": 2400
    },
    {
      "category": "ACCESSORY",
      "price": 2000
    }
  ],
  "totalPrice": 36100
}
```

<br>

### 3. 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API

#### Request

- HTTP METHOD: GET
- EndPoint: /api2
- QueryString: category (ex: TOP)

#### Response

- Status: 200

```json
{
    "category": "TOP",
    "maxAndMin": [
        {
            "brand": "C",
            "price": 10000
        },
        {
            "brand": "I",
            "price": 11400
        }
    ]
}
```

<br>

### 4-1. 상품 데이터 Create API

- HTTP Method: POST
- EndPoint: /

#### Request

```json
{
  "category": Category,
  "brand": String,
  "price": Int
}
```

#### Response

- Status: 201

<br>

### 4-2 상품 데이터 Update API

- HTTP Method: PATCH
- EndPoint: /
- PathVariable: {id} | 상품ID

#### Request

> category, brand, price 값중 입력하지 않은 값이 있다면 이전 값 유지

```json
{
  "category": Category?,
  "brand": String?,
  "price": Int?
}
```

#### Response

- Status: 204

<br>

### 4-3 상품 데이터 Delete API

- HTTP Method: DELETE
- EndPoint: /
- PathVariable: {id} | 상품ID

### Response

- Status: 204