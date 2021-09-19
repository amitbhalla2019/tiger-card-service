# tiger-card-service
A simple app to help riders to use a Metro Service, where cost of rides are calculated plus some offers

We can assume that all the users (the so-called travellers) would be able to use the services of the Metro by what is issued to them as a Tiger-Metro-Smart-Card

The swipes of the card going to dictate all the processing that happens in the backend

The time the user swipes his or her card can give an idea about which time ( whether peak hours or not), and even the day of the week, the user is trying to either board a Metro from any station or just exit a station after having travelled a certain distance, we can call it as a Ride / Transit

The swiping of a card would also take into account which station the card is being swiped, and if this station belongs to either of Zone-1 or Zone-2 stations

Therefore, when the locations are being captured by the card either through some kind of NFC or may be the scanner machine takes the responsibility of logging in the locations, this would also help to deduce the price of the ride, the user has to pay for

For developing purposes, we have tried to post certain requests through PostMan as using body as :

{
    "travellerId" : 121,
    "zone" : "Zone-1"
}

or

{
    "travellerId" : 121,
    "zone" : "Zone-2"
}

to localhost:8090/card-swipes [POST]

while time of card swipes would automatically get logged, the time the user swipes the card for an entry or exit

We have used MongoDB as the internal storage engine / database for storing all the date

We have also started the application by inputting through code all the tariff plans, peaks hours details, a few static data about the travellers, that can help focus on the main development

We have different controllers set to manage all of these entities but the bare minimum is showcased

In advanced stages, we can even think of introducing security and all such aspects
