# OOP-Course \ Laboratory 4

### API Overview
Application have full support of endpoints, requested by laboratory condition:

- *'SubmitCalculations'* for checking polynomial quadratic form significance   
  ```http
  POST /calculations/quadratic-form-significance
  Content-Type: application/json
  
  {
      "task": {
          "@type": "polynomial",
          "expression": "2x1x2-3x1^2-4x2^2"
      }
  }
  ```
  This request will send stored calculation identifier as response.


- *'LoadCalculationsResults'* for loading results by identifier from *'SubmitCalculations'* response
  ```http
  GET /calculations/repository/1
  Content-Type: application/json
  
  { "loadSolution": true }
  ```
  This request will respond with json-serialized *EvalResult* object, containing solution tree and evaluation result.
  ```http
  GET /calculations/repository/1
  Content-Type: application/json
  
  { "loadSolution": true }
  ```
  This request will respond with json-serialized evaluation result object only.

### Useful links
- [***[JSONGrid]***](https://jsongrid.com/json-viewer) - **convenient service for viewing json data - may be useful for application tests.**