package com.grdj.devigettest.resources

interface ResourcesProvider {
    fun getNotConnectedMessage() : String
    fun getApiError() : String
    fun getDeleteDBErrorr() : String
    fun getDeleteDBSuccess() : String
    fun getAPI_URL() : String
}