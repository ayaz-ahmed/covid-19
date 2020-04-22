Architecture:
    Using a Layered pattern architecture, controller communicate with service layer which further get data
    from utilities.
    After application starts a csv file will generate and that csv file will update every 8 Am through 
    a scheduler.
    
API EndPoint:
         To get a JWT token
POST     http://localhost:7079/authenticate
Payload:
    {"username":"admin", "password":"admin"}
    
       To get list of users currently accessing the api
GET    http://localhost:7079/users

       All new cases reported today
GET    http://localhost:7079/newCases        

        All new cases reported today country wise (sorted by cases reported today descending)
GET     http://localhost:7079/sortedNewCases

        All new cases reported today in a country
GET     http://localhost:7079/newCasesInCountry/{countryName}  

        All new cases reported since a date in a country              
POST   http://localhost:7079/reportedCasesSince
Payload:
        {
        	"fromDate":"2020-04-13",
        	"toDate":"2020-04-17",
        	"countryName":"US"
        }
        
        Top N countries with most reported cases today
GET     http://localhost:7079/mostReportedNewCases                        