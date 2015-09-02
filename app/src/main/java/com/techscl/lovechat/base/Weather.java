package com.techscl.lovechat.base;

import java.util.List;

/**
 * Created by songchunlin on 15/9/2.
 */
public class Weather {

    /**
     * data : [{"AreaID":"101070201","Year":2015,"Month":9,"Day":2,"Weather":"晴","WeatherICON":"n00","TempMax":20,"TempMin":20,"WindA":"无持续风向","WindB":"","Wind":"4-5级","Date":"2015-9-2 0:00:00"},{"AreaID":"101070201","Year":2015,"Month":9,"Day":3,"Weather":"晴","WeatherICON":"d00","TempMax":28,"TempMin":21,"WindA":"无持续风向","WindB":"无持续风向","Wind":"4-5级","Date":"2015-9-3 0:00:00"},{"AreaID":"101070201","Year":2015,"Month":9,"Day":4,"Weather":"晴转多云","WeatherICON":"d00","TempMax":28,"TempMin":21,"WindA":"无持续风向","WindB":"北风","Wind":"4-5级","Date":"2015-9-4 0:00:00"},{"AreaID":"101070201","Year":2015,"Month":9,"Day":5,"Weather":"多云","WeatherICON":"d01","TempMax":26,"TempMin":21,"WindA":"东南风","WindB":"东南风","Wind":"4-5级","Date":"2015-9-5 0:00:00"},{"AreaID":"101070201","Year":2015,"Month":9,"Day":6,"Weather":"多云","WeatherICON":"d01","TempMax":25,"TempMin":20,"WindA":"东南风","WindB":"南风","Wind":"4-5级","Date":"2015-9-6 0:00:00"},{"AreaID":"101070201","Year":2015,"Month":9,"Day":7,"Weather":"多云转晴","WeatherICON":"d01","TempMax":26,"TempMin":20,"WindA":"南风","WindB":"南风","Wind":"4-5级","Date":"2015-9-7 0:00:00"},{"AreaID":"101070201","Year":2015,"Month":9,"Day":8,"Weather":"多云","WeatherICON":"d01","TempMax":26,"TempMin":20,"WindA":"南风","WindB":"南风","Wind":"4-5级","Date":"2015-9-8 0:00:00"}]
     * data24 : [{"AreaID":"101070201","Year":2015,"Month":9,"Day":2,"BeginHour":20,"EndHour":23,"Weather":"晴","WeatherICON":"n00","TempMax":24,"TempMin":21,"Wind":"4-5级","WindD":"无持续风向","Date":"2015-9-2 0:00:00"},{"AreaID":"101070201","Year":2015,"Month":9,"Day":2,"BeginHour":23,"EndHour":2,"Weather":"晴","WeatherICON":"n00","TempMax":21,"TempMin":20,"Wind":"4-5级","WindD":"无持续风向","Date":"2015-9-2 0:00:00"},{"AreaID":"101070201","Year":2015,"Month":9,"Day":2,"BeginHour":2,"EndHour":5,"Weather":"晴","WeatherICON":"n00","TempMax":20,"TempMin":20,"Wind":"4-5级","WindD":"无持续风向","Date":"2015-9-2 0:00:00"},{"AreaID":"101070201","Year":2015,"Month":9,"Day":2,"BeginHour":5,"EndHour":8,"Weather":"晴","WeatherICON":"d00","TempMax":25,"TempMin":20,"Wind":"4-5级","WindD":"无持续风向","Date":"2015-9-2 0:00:00"}]
     * datanow : {"AreaID":"101070201","Year":2015,"Month":9,"Day":2,"BeginHour":20,"EndHour":23,"Weather":"晴","WeatherICON":"n00","TempMax":24,"TempMin":21,"Wind":"4-5级","WindD":"无持续风向","Date":"2015-9-2 0:00:00"}
     * pm25 : 34
     */

    private DatanowEntity datanow;
    private int pm25;
    private List<DataEntity> data;
    private List<Data24Entity> data24;

    public DatanowEntity getDatanow() {
        return datanow;
    }

    public void setDatanow(DatanowEntity datanow) {
        this.datanow = datanow;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<Data24Entity> getData24() {
        return data24;
    }

    public void setData24(List<Data24Entity> data24) {
        this.data24 = data24;
    }

    public static class DatanowEntity {
        /**
         * AreaID : 101070201
         * Year : 2015
         * Month : 9
         * Day : 2
         * BeginHour : 20
         * EndHour : 23
         * Weather : 晴
         * WeatherICON : n00
         * TempMax : 24
         * TempMin : 21
         * Wind : 4-5级
         * WindD : 无持续风向
         * Date : 2015-9-2 0:00:00
         */

        private String AreaID;
        private int Year;
        private int Month;
        private int Day;
        private int BeginHour;
        private int EndHour;
        private String Weather;
        private String WeatherICON;
        private int TempMax;
        private int TempMin;
        private String Wind;
        private String WindD;
        private String Date;

        public String getAreaID() {
            return AreaID;
        }

        public void setAreaID(String AreaID) {
            this.AreaID = AreaID;
        }

        public int getYear() {
            return Year;
        }

        public void setYear(int Year) {
            this.Year = Year;
        }

        public int getMonth() {
            return Month;
        }

        public void setMonth(int Month) {
            this.Month = Month;
        }

        public int getDay() {
            return Day;
        }

        public void setDay(int Day) {
            this.Day = Day;
        }

        public int getBeginHour() {
            return BeginHour;
        }

        public void setBeginHour(int BeginHour) {
            this.BeginHour = BeginHour;
        }

        public int getEndHour() {
            return EndHour;
        }

        public void setEndHour(int EndHour) {
            this.EndHour = EndHour;
        }

        public String getWeather() {
            return Weather;
        }

        public void setWeather(String Weather) {
            this.Weather = Weather;
        }

        public String getWeatherICON() {
            return WeatherICON;
        }

        public void setWeatherICON(String WeatherICON) {
            this.WeatherICON = WeatherICON;
        }

        public int getTempMax() {
            return TempMax;
        }

        public void setTempMax(int TempMax) {
            this.TempMax = TempMax;
        }

        public int getTempMin() {
            return TempMin;
        }

        public void setTempMin(int TempMin) {
            this.TempMin = TempMin;
        }

        public String getWind() {
            return Wind;
        }

        public void setWind(String Wind) {
            this.Wind = Wind;
        }

        public String getWindD() {
            return WindD;
        }

        public void setWindD(String WindD) {
            this.WindD = WindD;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
        }
    }

    public static class DataEntity {
        /**
         * AreaID : 101070201
         * Year : 2015
         * Month : 9
         * Day : 2
         * Weather : 晴
         * WeatherICON : n00
         * TempMax : 20
         * TempMin : 20
         * WindA : 无持续风向
         * WindB :
         * Wind : 4-5级
         * Date : 2015-9-2 0:00:00
         */

        private String AreaID;
        private int Year;
        private int Month;
        private int Day;
        private String Weather;
        private String WeatherICON;
        private int TempMax;
        private int TempMin;
        private String WindA;
        private String WindB;
        private String Wind;
        private String Date;

        public String getAreaID() {
            return AreaID;
        }

        public void setAreaID(String AreaID) {
            this.AreaID = AreaID;
        }

        public int getYear() {
            return Year;
        }

        public void setYear(int Year) {
            this.Year = Year;
        }

        public int getMonth() {
            return Month;
        }

        public void setMonth(int Month) {
            this.Month = Month;
        }

        public int getDay() {
            return Day;
        }

        public void setDay(int Day) {
            this.Day = Day;
        }

        public String getWeather() {
            return Weather;
        }

        public void setWeather(String Weather) {
            this.Weather = Weather;
        }

        public String getWeatherICON() {
            return WeatherICON;
        }

        public void setWeatherICON(String WeatherICON) {
            this.WeatherICON = WeatherICON;
        }

        public int getTempMax() {
            return TempMax;
        }

        public void setTempMax(int TempMax) {
            this.TempMax = TempMax;
        }

        public int getTempMin() {
            return TempMin;
        }

        public void setTempMin(int TempMin) {
            this.TempMin = TempMin;
        }

        public String getWindA() {
            return WindA;
        }

        public void setWindA(String WindA) {
            this.WindA = WindA;
        }

        public String getWindB() {
            return WindB;
        }

        public void setWindB(String WindB) {
            this.WindB = WindB;
        }

        public String getWind() {
            return Wind;
        }

        public void setWind(String Wind) {
            this.Wind = Wind;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
        }
    }

    public static class Data24Entity {
        /**
         * AreaID : 101070201
         * Year : 2015
         * Month : 9
         * Day : 2
         * BeginHour : 20
         * EndHour : 23
         * Weather : 晴
         * WeatherICON : n00
         * TempMax : 24
         * TempMin : 21
         * Wind : 4-5级
         * WindD : 无持续风向
         * Date : 2015-9-2 0:00:00
         */

        private String AreaID;
        private int Year;
        private int Month;
        private int Day;
        private int BeginHour;
        private int EndHour;
        private String Weather;
        private String WeatherICON;
        private int TempMax;
        private int TempMin;
        private String Wind;
        private String WindD;
        private String Date;

        public String getAreaID() {
            return AreaID;
        }

        public void setAreaID(String AreaID) {
            this.AreaID = AreaID;
        }

        public int getYear() {
            return Year;
        }

        public void setYear(int Year) {
            this.Year = Year;
        }

        public int getMonth() {
            return Month;
        }

        public void setMonth(int Month) {
            this.Month = Month;
        }

        public int getDay() {
            return Day;
        }

        public void setDay(int Day) {
            this.Day = Day;
        }

        public int getBeginHour() {
            return BeginHour;
        }

        public void setBeginHour(int BeginHour) {
            this.BeginHour = BeginHour;
        }

        public int getEndHour() {
            return EndHour;
        }

        public void setEndHour(int EndHour) {
            this.EndHour = EndHour;
        }

        public String getWeather() {
            return Weather;
        }

        public void setWeather(String Weather) {
            this.Weather = Weather;
        }

        public String getWeatherICON() {
            return WeatherICON;
        }

        public void setWeatherICON(String WeatherICON) {
            this.WeatherICON = WeatherICON;
        }

        public int getTempMax() {
            return TempMax;
        }

        public void setTempMax(int TempMax) {
            this.TempMax = TempMax;
        }

        public int getTempMin() {
            return TempMin;
        }

        public void setTempMin(int TempMin) {
            this.TempMin = TempMin;
        }

        public String getWind() {
            return Wind;
        }

        public void setWind(String Wind) {
            this.Wind = Wind;
        }

        public String getWindD() {
            return WindD;
        }

        public void setWindD(String WindD) {
            this.WindD = WindD;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
        }
    }
}
