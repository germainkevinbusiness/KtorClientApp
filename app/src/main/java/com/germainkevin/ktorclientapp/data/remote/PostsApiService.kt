package com.germainkevin.ktorclientapp.data.remote

import com.germainkevin.ktorclientapp.data.remote.dto.PostRequest
import com.germainkevin.ktorclientapp.data.remote.dto.PostResponse
import com.germainkevin.ktorclientapp.data.remote.impl.PostsApiServiceImpl
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface PostsApiService {

    suspend fun getPosts(): List<PostResponse>

    suspend fun createPost(postRequest: PostRequest): PostResponse?

    companion object {
        fun create(): PostsApiService {
            return PostsApiServiceImpl(client = HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
            })
        }
    }

}