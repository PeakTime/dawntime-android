package com.peaktime.dawntime.Shop

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peaktime.dawntime.R
import android.graphics.Typeface
import android.support.v4.content.res.ResourcesCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.shop_kind_item.view.*


/**
 * Created by xlsdn on 2018-01-04.
 */
class ShopKindAdapter(var dataList : ArrayList<ShopKindData>?) : RecyclerView.Adapter<ShopKindViewHolder>() {

    private var onItemClick : View.OnClickListener? = null
    private var typeface : Typeface?=null
    private var mainView : View?=null

    fun setOnItemClickListener(l: View.OnClickListener){
        onItemClick = l
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShopKindViewHolder {
        mainView = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.shop_kind_item, parent, false)

        mainView!!.setOnClickListener(onItemClick)

        return ShopKindViewHolder(mainView)
    }

    //어뎁터와 뷰홀더를 포디션에 맞게 연결하는 부분
    override fun onBindViewHolder(holder: ShopKindViewHolder?, position: Int) {
        holder!!.kindName.setText(dataList!!.get(position).kindName)


        mainView!!.kind_name_textview.setOnClickListener{

            set_font(holder, position)

//            mainView!!.kind_name_textview.setText("바뀜")
//            typeface = Typeface.createFromAsset(mainView!!.getContext().getAssets(), R.font.noto_sans_cjk_kr_medium)
//            mainView!!.kind_name_textview.setTypeface(typeface)

        }



    }

    fun set_font(holder: ShopKindViewHolder?, position: Int){

//        dataList!!.get(position).kindName.equals("바이브레이터")

        if(position==0){

//            typeface = ResourcesCompat.getFont(this, R.font.noto_sans_cjk_kr_medium)
            Toast.makeText(mainView!!.context, position.toString(), Toast.LENGTH_SHORT).show()
            typeface = ResourcesCompat.getFont(holder!!.kindName.context, R.font.noto_sans_cjk_kr_medium)
            holder!!.kindName.setTypeface(typeface)

        }else if(dataList!!.get(position).kindName.equals("커플토이")){

            Toast.makeText(mainView!!.context, position.toString(), Toast.LENGTH_SHORT).show()
            typeface = ResourcesCompat.getFont(holder!!.kindName.context, R.font.noto_sans_cjk_kr_medium)
            holder!!.kindName.setTypeface(typeface)

        }else{

            typeface = ResourcesCompat.getFont(holder!!.kindName.context, R.font.noto_sans_cjk_kr_light)
            holder!!.kindName.setTypeface(typeface)

        }

    }

    //리턴값이 간단할때 이렇게 사용
    override fun getItemCount(): Int = dataList!!.size

//    override fun getItemCount(): Int{
//        return dataList!!.size
//    }




}