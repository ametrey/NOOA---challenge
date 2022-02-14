# NOOA-api
NOAA Bouys Manager

This API is a fictional manager of buoys and samples for NOAA.

This is only for educational purpose.

With this manager, you can create a buoy indicating its longitude and latitude on the sea. Then you can create samples indicating the taking hour, latitude, longitude and the current level of water elevation of the buoy.

BLUE is default colour of the light on the bouy when it's created.

When you add a Sample to a Bouy, the light will turn to:

GREEN: when the difference between the starting sea level and the new sample sea level is less than 50 mtrs.

YELLOW: when the difference between the starting sea level and the new sample sea level is between 51 and 100 mtrs.

RED: when the difference between the starting sea level and the new sample sea level is above 100 mtrs.

It has an Anomaly alert: when the difference on sea level on two samples equals 200 mtrs and the lapsed time is above 10 minutes it returns a KAIJU alert (GODZILLA is coming!!!).
when the difference on sea level on two samples is more than 500 mtrs it returns an IMPACT alert (Meteorite or Alien vessel).

The API endpoints are in Spanish because the challenge is in that language.

No authentication needed.

POSTMAN Doc: https://documenter.getpostman.com/view/16169993/U16gNmaw

Heroku App: https://buoys-manager.herokuapp.com/buoys-manager.html

Database Schema:

<a href="https://imgbb.com/"><img src="https://i.ibb.co/LnQzQvF/diagramaboyassql.png" alt="diagramaboyassql" border="0"></a>




