# Line-Chart
#### Simple library for android Line Chart view
#### It is a polished version of [LineChart](https://github.com/VladlinMoiseenko/line-chart) library by [VladlinMoiseenko](https://github.com/VladlinMoiseenko)
#### It can be used with viewbinding too

### Download
#### Gradle

```gradle
allprojects {
  repositories {
    ..
    maven {url 'https://jitpack.io'}
  }

}

dependencies {
..
implementation 'com.github,mridx:Line-Chart:0.1'
..
}
```

### Usage
#### Layout
```XML
<com.mridx.linechart.LineChart
        android:id="@+id/lineChart"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        app:circleRadius="1dp"
        app:lineColor="#1565C0"
        app:lineWidth="2dp"
        android:background="#ECF2F8"
        />
```
#### Activity or Fragment
```JAVA

lineChart = findViewById(R.id.lineChart);
lineChart.setDataChart(new float[]{5, 1, 2, 3, 5, 6, 7, 8, 9, 8, 13, 10});


//with viewbinding
binding.lineChart.setDataChart(new float[]{5, 1, 2, 3, 5, 6, 7, 8, 9, 8, 13, 10});
```

### Screenshot
![Screenshot](ss.jpg)
