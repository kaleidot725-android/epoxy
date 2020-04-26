# 2020/04/25 ［Android］ Epoxy のサンプルと解説

# はじめに

RecyclerView を使う際には Adapter などに独自で処理を実装する必要があり、ここが RecyelerView を利用する上でかなり面倒な実装だったりします。 Epoxy はこの面倒な処理を実装してくれるライブラリらしいです。Epoxy のサンプルを作成してどんなことができるのかざっくりと解説していきたいと思います。

# 準備する

Epoxy を利用できるようにセットアップを進めます。ライブラリとして epoxy、 epoxy-databinding, epoxy-processor を追加します。databinding を利用する際には epoxy-databinding が必要になります、必要なければ追加する必要はないです。す。ライブラリを追加したら kapt と databinding を有効化するだけです。これでセットアップは完了です。

```groovy
// kapt 有効化
apply plugin: 'kotlin-kapt' 

android {
      ︙
    // databinding 有効化
    dataBinding {
        enabled = true 
    }
      ︙
}

dependencies {
      ︙
    def epoxy_version = "3.9.0"
    implementation "com.airbnb.android:epoxy:$epoxy_version"
    implementation "com.airbnb.android:epoxy-databinding:${epoxy_version}"
    kapt "com.airbnb.android:epoxy-processor:$epoxy_version"
      ︙
}
```

# 実装する

## EpoxyModel を作成する

EpoxyModel を作成する方法は 3種類あるみたいです。CustomViewから作成する方法、Daabinding から作成する方法、 ViewHolder から作成する方法とあります。今回は CustomView と Daabinding から作成するのを試してみたいと思います。ViewHolder から作成する方法使わないだろうと覆ったので省きます。

### CustomView から作成する

```kotlin
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class HeaderCustomView : LinearLayout {
    private lateinit var titleView: TextView
    private lateinit var buttonView: Button

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        titleView = TextView(context, attrs)
        titleView.width = 500
        titleView.textSize = 32f
        this.addView(titleView)

        buttonView = Button(context, attrs)
        buttonView.text = "ACTION"
        this.addView(buttonView)
    }

    @TextProp
    fun setTitle(text: CharSequence?) {
        titleView.text = text
    }

    @CallbackProp
    fun onClickListener(listener: View.OnClickListener?) {
        buttonView.setOnClickListener(listener)
    }
}
```

### DataBinding から作成する


```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>

        <import type="android.view.View" />

        <variable
            name="title"
            type="String" />

        <variable
            name="onClickListener"
            type="View.OnClickListener" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="5dp">

        <TextView
            android:id="@+id/title_text_view"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:text="@{title}"
            android:textSize="32sp"
            app:layout_constraintBottom_toBottomOf="@id/action_button"
            app:layout_constraintEnd_toStartOf="@id/action_button"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="@id/action_button"
            tools:text="XXXXXXXXXXXXXXX" />

        <Button
            android:id="@+id/action_button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="ACTION"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:setOnClickListener="@{onClickListener}" />

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```

## EpoxyController を作成する

### CustomView

```kotlin
class HeaderCustomViewController(
    private val selectListener: SelectListener
) : Typed2EpoxyController<List<String>, Boolean>() {

    override fun buildModels(names: List<String>, loadingMore: Boolean) {
        names.forEach { item ->
            headerCustomView{
                id("Content")
                title(item)
                onClickListener(View.OnClickListener { selectListener.onSelected(item) })
            }
        }
    }

    interface SelectListener {
        fun onSelected(item: String)
    }
}
```

### Databinding

```kotlin
class HeaderDatabindingViewController(
    private val selectListener: SelectListener
) : Typed2EpoxyController<List<String>, Boolean>() {

    override fun buildModels(names: List<String>, loadingMore: Boolean) {
        names.forEach { item ->
            headerDataBindingLayout {
                id("Content")
                title(item)
                onClickListener(View.OnClickListener { selectListener.onSelected(item) })
            }
        }
    }

    interface SelectListener {
        fun onSelected(item: String)
    }
}
```

# 動作確認する

```kotlin
class MainActivity : AppCompatActivity() {
    private val itemList = listOf(
        "ONE", "TWO", "THREE", "FOUR", "FIVE",
        "SIX", "SEVEN", "EIGHT", "NINE", "TEN"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val headerDatabindingViewController = HeaderDatabindingViewController(object :
            HeaderDatabindingViewController.SelectListener {
            override fun onSelected(item: String) {
                Toast.makeText(applicationContext, item, Toast.LENGTH_SHORT).show()
            }
        })

        recycler_view.apply {
            this.adapter = headerDatabindingViewController.adapter
            this.layoutManager = LinearLayoutManager(applicationContext).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
        }

        headerDatabindingViewController.setData(itemList, false)

        val headerCustomViewController =
            HeaderCustomViewController(object : HeaderCustomViewController.SelectListener {
                override fun onSelected(item: String) {
                    Toast.makeText(applicationContext, item, Toast.LENGTH_SHORT).show()
                }
            })

        recycler_view2.apply {
            this.adapter = headerCustomViewController.adapter
            this.layoutManager = LinearLayoutManager(applicationContext).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
        }

        headerCustomViewController.setData(itemList, false)
    }
}
```

[![Image from Gyazo](https://i.gyazo.com/2078d66fdf3ae692c2977125ccde7aa9.png)](https://gyazo.com/2078d66fdf3ae692c2977125ccde7aa9)

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="0dp"
        android:layout_height="match_parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toStartOf="@id/recycler_view2"/>

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recycler_view2"
        android:layout_width="0dp"
        android:layout_height="match_parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toEndOf="@id/recycler_view" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

# おわりに

# 参考文献

- [Epoxy | GitHub](https://github.com/airbnb/epoxy)