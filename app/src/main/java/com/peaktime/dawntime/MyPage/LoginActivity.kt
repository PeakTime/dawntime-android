package com.peaktime.dawntime.MyPage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageButton
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import com.peaktime.dawntime.SharedPreferInstance
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    val OAUTH_CLIENT_ID = "SMSrJ5GLSLRWZ0lXc6QE"
    val OAUTH_CLIENT_SECRET = "h74JENKxq6"
    val OAUTH_CLIENT_NAME = "네이버 아이디로 로그인"

    var networkService: NetworkService? = null
    var signData: SignInData? = null
    var mLoginButton: OAuthLoginButton? = null
    var mAuthLoginModule: OAuthLogin? = null
    var mFakeLoginButton : ImageButton? = null
    var mContext: Context? = null

    var email: String? = null
    var gender: String? = null
    var naver_uid: String? = null
    var age: String? = null

//    var profileGet = NaverProfileGet()

//    private val mOAuthLoginHandler = object : OAuthLoginHandler() {
//
//        override fun run(success: Boolean) {
//            if (success) {
//                accessToken = mAuthLoginModule!!.getAccessToken(mContext)
//                val refreshToken = mAuthLoginModule!!.getRefreshToken(mContext)
//                val expiresAt = mAuthLoginModule!!.getExpiresAt(mContext)
//                val tokenType = mAuthLoginModule!!.getTokenType(mContext)
//                Log.i("액세스 토큰 ",accessToken)
//                //profileGet.execute()
//            } else {
//                val errorCode = mAuthLoginModule!!.getLastErrorCode(mContext).code
//                val errorDesc = mAuthLoginModule!!.getLastErrorDesc(mContext)
//                Toast.makeText(mContext, "errorCode: " + errorCode + "errorDesc: "+errorDesc, Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mContext = this
        mAuthLoginModule = OAuthLogin.getInstance()
        mAuthLoginModule!!.init(
                mContext,
                OAUTH_CLIENT_ID,
                OAUTH_CLIENT_SECRET,
                OAUTH_CLIENT_NAME
        )
        networkService = ApplicationController.instance!!.networkService

        mFakeLoginButton = findViewById(R.id.loginFakeBtn)
        mFakeLoginButton!!.setOnClickListener {
            mLoginButton!!.performClick()
        }

        mLoginButton = findViewById(R.id.loginBtn)
        mLoginButton!!.setOAuthLoginHandler(object : OAuthLoginHandler() {
            override fun run(b: Boolean) {
                if (b) {
                    val token = mAuthLoginModule!!.getAccessToken(this@LoginActivity)
                    Thread(Runnable {
                        val response = mAuthLoginModule!!.requestApi(this@LoginActivity, token, "https://openapi.naver.com/v1/nid/me")
                        try {
                            val json = JSONObject(response)
                            // response 객체에서 원하는 값 얻어오기
                            email = json.getJSONObject("response").getString("email")
                            gender = json.getJSONObject("response").getString("gender")
                            naver_uid = json.getJSONObject("response").getString("id")
                            age = json.getJSONObject("response").getString("age")
//                            Log.i("나이",age + gender)
//                            if(gender.equals("M"))
//                            {
//                                mAuthLoginModule!!.logout(this@LoginActivity)
//                                if (mAuthLoginModule!!.getState(this@LoginActivity) == OAuthLoginState.NEED_LOGIN) {
////                                    ApplicationController.instance!!.makeToast("남성은 가입할 수 없습니다.")
//                                    Log.i("나이2",age + gender)
//                                }
//                                finish()
//                            }

                            signIn()
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }).start()
                } else {
                }
            }
        })
    }

    fun signIn() {
        var getContentList = networkService!!.signIn(email!!, naver_uid!!)
        getContentList.enqueue(object : Callback<SignInResponse> {
            override fun onResponse(call: Call<SignInResponse>?, response: Response<SignInResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().message.equals("successful sign in")) {

                        signData = response.body().result
                        SharedPreferInstance.getInstance(applicationContext).putPreferString("EMAIL", email!!)
                        SharedPreferInstance.getInstance(applicationContext).putPreferString("GENDER", gender!!)
                        SharedPreferInstance.getInstance(applicationContext).putPreferString("AGE", age!!)
                        SharedPreferInstance.getInstance(applicationContext).putPreferString("NAVER_UID", naver_uid!!)
                        SharedPreferInstance.getInstance(applicationContext).putPreferBoolean("LOGIN", true)
                        SharedPreferInstance.getInstance(applicationContext).putPreferString("TOKEN", signData!!.user_token)
                        SharedPreferInstance.getInstance(applicationContext).putPreferInt("UID", signData!!.user_id)
                        Log.i("LoginCheck : ", "로그인 되었습니다.")
                        Log.i("Token : ", signData!!.user_token)

                        var intent = Intent()
                        intent.putExtra("EMAIL", email)
                        setResult(Activity.RESULT_OK, intent)
                        finish()
                    }
                } else {
                    Log.i("status", "fail")
                }
            }

            override fun onFailure(call: Call<SignInResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }

//
//    inner class NaverProfileGet : AsyncTask<Void,Void,String>()
//    {
//        var header = "Bearer " + accessToken
//
//        override fun doInBackground(vararg params: Void?): String {
//            var response = StringBuffer()
//            try {
//                var apiURL = "https://openapi.naver.com/v1/nid/me"
//                var url = URL(apiURL)
//                var conn = url.openConnection() as HttpURLConnection
//                conn.requestMethod = "GET"
//                conn.setRequestProperty("Authorization",header)
//
//                var responseCode = conn.responseCode
//
//                var br : BufferedReader
////                if(responseCode == 200)
////                {
////                    br = BufferedReader(InputStreamReader(conn.inputStream))
////                    Log.i("감?????????","dddd")
////                }
//                br = BufferedReader(InputStreamReader(conn.inputStream))
//                Log.i("감?????????","dddd")
////                else
////                {
////                    br = BufferedReader(InputStreamReader(conn.errorStream))
////                    Log.i("감?????????","ㄴㄴㄴㄴㄴ")
////                }
//
//
//                var inputLine = br.readLine()
//                Log.i("감?????????",inputLine)
//                while(inputLine != null)
//                {
//                    Log.i("확인확인확인확인",inputLine)
//                    response.append(inputLine)
//                    inputLine = br.readLine()
//                }
//                br.close()
//            }catch (e : Exception ){}
//            return response.toString()
//        }
//
//
//        override fun onPostExecute(result: String?) {
//            super.onPostExecute(result)
//
//            Toast.makeText(applicationContext,result.toString(),Toast.LENGTH_LONG).show()
//            try{
//                var intent = Intent(this@LoginActivity,MainActivity::class.java)
//                val jsonObject1 = JSONObject(result)
//                val jsonObject2 = jsonObject1.get("response") as JSONObject
//
//                var email = jsonObject2.getString("email")
//                var gender = jsonObject2.getString("gender")
//
//                intent.putExtra("EMAIL",email)
//                intent.putExtra("GENDER",gender)
//                startActivity(intent)
//                finish()
//            }catch(e : Exception){
//                Toast.makeText(applicationContext,"안된ㄴ이ㅏㅓ니ㅏ러",Toast.LENGTH_SHORT).show()
//                e.printStackTrace()
//            }
//
//        }
//
//        override fun onCancelled() {
//            super.onCancelled()
//        }
//    }
}
