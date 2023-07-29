export interface Coordintates{
    lat:number,lng:number
}

export interface PlaceLocation extends Coordintates{
    address:string,
    staticMNapImgUrl:string
}