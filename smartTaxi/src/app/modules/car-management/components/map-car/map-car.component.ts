import { Component, OnInit } from '@angular/core';
import * as mapboxgl from 'mapbox-gl';
import { Map } from 'mapbox-gl';
import { CarService } from '../../services/car.service';

@Component({
  selector: 'app-map-car',
  templateUrl: './map-car.component.html',
  styleUrls: ['./map-car.component.scss']
})
export class MapCarComponent implements OnInit {
  map: mapboxgl.Map | undefined;
  style = 'mapbox://styles/mapbox/streets-v11';
  lat: number = 36.712493;
  lng: number = 10.2072073;

  constructor(private carServ: CarService) {}

  ngOnInit() {
    (mapboxgl as typeof mapboxgl).accessToken = 'pk.eyJ1IjoiY2hhcmxpZTQ0NyIsImEiOiJjbGVmcGhmZDcwdDc4M29tanRnOG5hbHUwIn0.gR8hNxBIUFLabHFrGfOYTw';

    this.map = new mapboxgl.Map({
      container: 'map',
      style: this.style,
      zoom: 15,
      center: [this.lng, this.lat]
    });

    this.map.on('load', () => {
     
      this.carServ.getAllCar().subscribe((carsData: any[]) => {
      
        this.map?.addSource('cars', {
          type: 'geojson',
          data: {
            type: 'FeatureCollection',
            features: carsData.map(car => {
              const randomLng = this.lng + (Math.random() - 0.5) * 0.02;
              const randomLat = this.lat + (Math.random() - 0.5) * 0.02;

              return {
                type: 'Feature',
                geometry: {
                  type: 'Point',
                  coordinates: [randomLng, randomLat]
                },
                properties: {
                  make: car.make,
                  model: car.model,
                  manufacturingDate: car.manufacturingDate,
                  registrationNumber: car.registrationNumber,
                  seatsNumber: car.seatsNumber
                }
              };
            })
          }
        });

       
  this.map?.loadImage('../../../../../assets/images/taxi.png', (error, image) => {
    if (error) throw error;

  
    if (this.map && image) { // Check if image is defined
    
      if (!this.map.hasImage('custom-marker')) {
        this.map.addImage('custom-marker', image);
      }

      // Add the symbol layer to the map using the custom marker
      this.map.addLayer({
        id: 'cars-layer',
        type: 'symbol',
        source: 'cars',
        layout: {
          'icon-image': 'custom-marker',
          'icon-size': 0.5, 
          'icon-allow-overlap': true,
        },
      });
            // Update car locations every 5 seconds
            setInterval(() => {
              this.updateCarLocations();
            }, 5000);
    }
  });
}
  
  
      )})}
      updateCarLocations() {
  // Fetch updated car data from the service
  this.carServ.getAllCar().subscribe((carsData: any[]) => {
    // Update the GeoJSON source with new car locations
    const source: mapboxgl.GeoJSONSource = this.map?.getSource('cars') as mapboxgl.GeoJSONSource;
    source.setData({
      type: 'FeatureCollection',
      features: carsData.map(car => {
        const randomLng = this.lng + 0.01;
        const randomLat = this.lat +  0.02;

        return {
          type: 'Feature',
          geometry: {
            type: 'Point',
            coordinates: [randomLng, randomLat]
          },
          properties: {
            make: car.make,
            model: car.model,
            manufacturingDate: car.manufacturingDate,
            registrationNumber: car.registrationNumber,
            seatsNumber: car.seatsNumber
          }
        };
      })
    });
  });
}
}
