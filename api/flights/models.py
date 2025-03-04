from django.db import models

class Airline(models.Model):
    name = models.CharField(max_length=255)
    
    def __str__(self):
        return self.name
    
class Airport(models.Model):
    name = models.CharField(max_length=255)
    code = models.CharField(max_length=3)
    
    def __str__(self):
        return self.name

class Continent(models.Model):
    name = models.CharField(max_length=255) 

    def __str__(self):
        return self.name
    
class Country(models.Model):
    continent = models.ForeignKey(Continent, on_delete=models.CASCADE)
    name = models.CharField(max_length=255)

    def __str__(self):
        return self.name
    
class FlightClass(models.Model):
    name = models.CharField(max_length=255)
    
    def __str__(self):
        return self.name

class Flight(models.Model):
    
    departures = models.ForeignKey(Country, models.CASCADE, related_name='departing_flights')
    arrivals = models.ForeignKey(Country, models.CASCADE, related_name='arriving_flights') 
    
    departure_airport = models.ForeignKey(Airport, models.CASCADE, related_name='departing_airport')
    destination_airport = models.ForeignKey(Airport, models.CASCADE, related_name='arriving_airport')
    
    departure_date = models.DateField()
    arrival_date = models.DateField()

    departure_time = models.TimeField()
    arrival_time = models.TimeField()
    
    airline = models.ForeignKey(Airline, models.CASCADE)
    
    flight_class = models.ForeignKey(FlightClass, models.CASCADE)

    price = models.PositiveIntegerField(default=0)
    gate = models.CharField(max_length=1)
    
    def save(self, *args, **kwargs):
        if self.departure_time:
            self.departure_time = self.departure_time.replace(second=0, microsecond=0)
        if self.arrival_time:
            self.arrival_time = self.arrival_time.replace(second=0, microsecond=0)
        super().save(*args, **kwargs)