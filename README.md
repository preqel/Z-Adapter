# Z-Adapter
一个简洁的帮助类封装了Recyclerview的adapter
# 如何引入
在项目的根配置文件里面增加
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
在项目的模块配置文件里面增加
dependencies {
  	implementation 'com.github.preqel:Z-Adapter:0.9'
}
# 用法

    recyclerView.adapter = object: CommonAdadpter<String>(this, R.layout.item_recye,datas) {


            override fun convert(holder: ViewHolder, t: String, position: Int) {
                val textview = holder.getView<TextView>(R.id.tv_text)
                textview.text = t
            }
        }
