# HeaderComponent

A HeaderView Component which can be used as Titlebar.
you can write a custom Annotation and parse it to a HeaderView.finally you can use as following code

```
@Title(value = "我是小金子", menuText = "菜单", menuClick = "onMenuClick")
public class MainAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
    }

    public void onMenuClick(View view) {
        Toast.makeText(this, "v= " + view, Toast.LENGTH_SHORT).show();
    }

}
```
