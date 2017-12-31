package com.peaktime.dawntime

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton
import org.json.JSONException
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {

    val OAUTH_CLIENT_ID = "SMSrJ5GLSLRWZ0lXc6QE"
    val OAUTH_CLIENT_SECRET = "h74JENKxq6"
    val OAUTH_CLIENT_NAME = "네이버 아이디로 로그인"

    var accessToken: String? = null
    var mLoginButton: OAuthLoginButton? = null
    var mAuthLoginModule: OAuthLogin? = null
    var mContext: Context? = null

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
                            val email = json.getJSONObject("response").getString("email")
                            val gender = json.getJSONObject("response").getString("gender")

                            var intent = Intent(applicationContext, MainActivity::class.java)
                            intent.putExtra("EMAIL", email)
                            intent.putExtra("GENDER", gender)
                            startActivity(intent)
                            finish()
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }).start()
                } else {
                }
            }
        })
        //        mLoginButton!!.setOAuthLoginHandler(mOAuthLoginHandler)

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
