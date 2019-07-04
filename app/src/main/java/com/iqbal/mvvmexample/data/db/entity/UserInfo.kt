package com.iqbal.mvvmexample.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val USER_ID = 0

@Entity(tableName = "user_table")

data class UserInfo(
    @SerializedName("user_id")
    var userId: Int? = null,
    var username: String? = null,
    var password: String? = null,
    var status: String? = null,
    @SerializedName("role_id")
    var roleId: Int? = null,
    var fullname: String? = null,
    @SerializedName("created_stamp")
    var createdStamp: String? = null,
    @SerializedName("branch_code")
    var branchCode: String? = null,
    @SerializedName("re_password")
    var rePassword: String? = null,
    var img: String? = null,
    @SerializedName("flag_akses_polis")
    var flagAksesPolis: Int? = null,
    @SerializedName("agen_code")
    var agenCode: String? = null,
    @SerializedName("user_type")
    var userType: String? = null,
    var token: String? = null
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = USER_ID
}