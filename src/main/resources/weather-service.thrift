namespace java com.thrift.generated

exception InvalidOperationException {
    1: i32 code,
    2: string description
}

struct WeatherData {
    1: string stationIp,
    2: string time,
    3: string temperature,
    4: string humidity,
    5: string windSpeed,
    6: bool rain,
}

service WeatherService {

    void save(1:WeatherData weatherData) throws (1:InvalidOperationException e),

    bool ping() throws (1:InvalidOperationException e)
}