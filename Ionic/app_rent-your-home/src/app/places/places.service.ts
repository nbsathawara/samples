import { Injectable } from '@angular/core';
import { Place } from './place.model';
import { AuthService } from '../auth/auth.service';
import { BehaviorSubject, delay, map, of, switchMap, take, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { PlaceLocation } from './location.model';


interface PlaceData {
  availableFrom: string
  availableTo: string
  desc: string
  imgUrl: string
  price: number
  title: string
  userId: string
  location: PlaceLocation
}

@Injectable({
  providedIn: 'root',
})
export class PlacesService {
  private _places = new BehaviorSubject<Place[]>([])
  //   [
  //   new Place(
  //     'p1',
  //     'Manhattan Mansion',
  //     'In the heart of New York City.',
  //     'https://lonelyplanetimages.imgix.net/mastheads/GettyImages-538096543_medium.jpg?sharp=10&vib=20&w=1200',
  //     149.99,
  //     new Date('2023-07-27'), new Date('2023-12-31'),
  //     'abc'
  //   ),
  //   new Place(
  //     'p2',
  //     "L'Amour Toujours",
  //     'A romantic place in Paris!',
  //     'https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/Paris_Night.jpg/1024px-Paris_Night.jpg',
  //     189.99,
  //     new Date('2023-07-27'), new Date('2023-12-31'),
  //     'abc'
  //   ),
  //   new Place(
  //     'p3',
  //     'The Foggy Palace',
  //     'Not your average city trip!',
  //     'https://upload.wikimedia.org/wikipedia/commons/0/01/San_Francisco_with_two_bridges_and_the_fog.jpg',
  //     99.99,
  //     new Date('2023-07-27'
  //     ), new Date('2023-12-31'),
  //     'xyz'
  //   )
  // ]

  constructor(private authService: AuthService, private http: HttpClient) { }

  get places() {
    return this._places.asObservable();
  }

  getPlace(id: string) {
    return this.http.get<PlaceData>(environment.baseUrl + `offered-places/${id}.json`)
      .pipe(map(placeData => {
        return new Place(id, placeData.title, placeData.desc, placeData.imgUrl,
          placeData.price, new Date(placeData.availableFrom), new Date(placeData.availableTo),
          placeData.userId, placeData.location)
      }))
  }

  fetchPlaces() {
    return this.http.get<{ [keys: string]: PlaceData }>(environment.baseUrl + 'offered-places.json')
      .pipe(map(resData => {
        const places: Place[] = []
        for (const key in resData) {
          if (resData.hasOwnProperty(key)) {

            const place = new Place(key, resData[key].title, resData[key].desc, resData[key].imgUrl,
              resData[key].price, new Date(resData[key].availableFrom), new Date(resData[key].availableTo),
              resData[key].userId, resData[key].location)

            places.push(place)
          }
        }
        return places
      }), tap(places => {
        this._places.next(places)
      }))
  }

  addPlace(title: string, desc: string, price: number, startDate: Date, endDate: Date, location: PlaceLocation, imgUrl: string) {
    const newPlace = new Place(Math.random().toString(), title, desc, imgUrl,
      price, startDate, endDate, this.authService.userId, location)


    let generatedId: string = ''
    const observer = {
      next: (places) => {
        newPlace.id = generatedId
        this._places.next(places.concat(newPlace))
        console.log('places : ' + places)
      },
      error: (error) => {
        console.log('error : ' + error)
      },
      complete: () => {
        console.log('completed.')
      },
    }

    return this.http.post<{ name: string }>(environment.baseUrl + 'offered-places.json',
      {
        ...newPlace, id: null
      }).pipe(
        switchMap(resData => {
          generatedId = resData.name
          return this.places
        }), take(1), tap(observer)

      )
  }

  updatePlace(placeId: string, title: string, desc: string) {
    let updatedPlaces!: Place[]
    return this.places.pipe(take(1),
      switchMap(places => {
        if (!places || places.length <= 0)
          return this.fetchPlaces()
        else return of(places)
      }
      ),
      switchMap(places => {
        const index = places.findIndex(p => p.id === placeId)
        updatedPlaces = [...places]
        const oldPlace = updatedPlaces[index] as Place
        updatedPlaces[index] = new Place(placeId, title, desc, oldPlace.imgUrl, oldPlace.price,
          oldPlace.availableFrom, oldPlace.availableTo,
          oldPlace.userId, oldPlace.location)
        console.log(updatedPlaces[index])
        return this.http.put(environment.baseUrl + `offered-places/${placeId}.json`,
          {
            ...updatedPlaces[index], id: null
          }
        )
      }),
      tap(resData => {
        console.log(resData)
        this._places.next(updatedPlaces)
      })
    )
  }

  uploadImage(image: File) {
    let index = Math.floor(Math.random() * this.imgs.length);
    let imgUrl = this.imgs[index]
    console.log(imgUrl)
    return imgUrl
    // const uploadData = new FormData()
    // uploadData.append('image', image)

    // return this.http.post<{ imageUrl: string, imagePath: string }>(
    //   'https://us-central1-ionic-angular-course.cloudfunctions.net/storeImage',
    //   uploadData
    // );
  }

  imgs = [
    'https://picsum.photos/seed/picsum/200/300',
    'https://fastly.picsum.photos/id/435/200/300.jpg?grayscale&hmac=sIIPB5XgUzv2gJ06dY0RTZiPBblJi7Rv7wb9WOfIXvU',
    'https://images.pexels.com/photos/2825578/pexels-photo-2825578.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940',
    'https://picsum.photos/id/237/200/300',
    'https://picsum.photos/1200/600',
    'https://images.unsplash.com/photo-1563713665854-e72327bf780e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80',
    'https://i2.wp.com/beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg',
    'https://lonelyplanetimages.imgix.net/mastheads/GettyImages-538096543_medium.jpg?sharp=10&vib=20&w=1200',
    'https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/Paris_Night.jpg/1024px-Paris_Night.jpg',
    'https://upload.wikimedia.org/wikipedia/commons/0/01/San_Francisco_with_two_bridges_and_the_fog.jpg',
  ]

}
