package com.example.lab01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { // OnCreate는 생성시 호출되는 함수.
        // 프레임워크가 액티비티 객체를 생성함 --> 액티비티 끼리의 차이가 없을 것 왜? 생성자가 없기 때문에.
        // 만들어졌고 초기화 작업을 해야하니까 onCreate를 호출하는것.
        // 앱을 누르고 화면에 보여지기 전까지 뭔가를 실행하는 그 전까지의 상태를 수행
        // 이 onCreate를 사실상 생성자라고 보면 됨

        super.onCreate(savedInstanceState); // 부모의 onCreate를 호출함. 액티비티가 실행,


       LinearLayout linear = new LinearLayout(this);

        Button bt = new Button(this);
        bt.setText("Button 1");
        linear.addView(bt);

        Button bt2 = new Button(this);
        bt2.setText("Button 2");
        linear.addView(bt2);

        // LinearLayout, Button 둘 다 위젯이고 그 위에 View라는 최상위 클래스가 있음.
        // Layout이 붙은 클래스는 자기 네모 영역에 다른 위젯을 올릴 수 있다.
        // Layout이 붙은 애들은 그냥 네모라고 생각 --> 다른 위젯을 올릴 컨테이너라고 생각하면 됨.
        // linear.addView(매개변수) 매개변수를 linear에 올린다는 뜻. linear는 버튼 두개를 가진 레이아웃이 될 것 이다.
        // 그 레이아웃(linear)을 setContentView(linear)로 내 첫 화면으로 띄울 것
        // 매개변수 this의 의미 --> type은 context (문맥) --> this는 MainActivity 객체를 의미
        // 액티비티 객체는 context 객체가 될 수 있다 --> context를 요구하는 곳에 액티비티 객체를 넣을 수 있다.
        // 액티비티는 프레임워크가 관리, 앱은 컴포넌트로 구성, 각종 앱에 각종 컴포넌트가 실행되고 있는데 그 실행은 프레임워크가 관리하고 있다.
        // 그래서 this를 통해 무슨 앱에 무슨 컴포넌트인지 알기위해 프레임워크가 계속해서 context를 요구하는 것.

        setContentView(linear);
        // ww
        // 내 화면을 (매개변수) 대로 화면을 setting 한다.
        // 매개변수는 int 형인데 코드에 경로가 드러나지 않는 장점이 있다.
        // ex) setContentView(R.layout.activity_second) --> res/layout에 xml파일을 만들어서 불러올 수 있음.
        // linear는 int 형이 아님, setContentView앞에 .이 안붙어있다 --> this. 이 생략된 것. --> 부모에 상속
        // setContentView 가 부모에 여러개 overload되어 있을 것이다. 정수형이 아니니까
    }
}