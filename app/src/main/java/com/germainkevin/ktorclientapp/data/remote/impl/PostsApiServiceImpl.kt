package com.germainkevin.ktorclientapp.data.remote.impl

import com.germainkevin.ktorclientapp.data.remote.HttpRoutes
import com.germainkevin.ktorclientapp.data.remote.PostsApiService
import com.germainkevin.ktorclientapp.data.remote.dto.PostRequest
import com.germainkevin.ktorclientapp.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.lang.Exception

class PostsApiServiceImpl(private val client: HttpClient) : PostsApiService {
    override suspend fun getPosts(): List<PostResponse> {
        return try {
            client.get {
                url(HttpRoutes.POSTS)
            }
        } catch (e: RedirectResponseException) {
            // 3xx responses
            println("Error: ${e.response.status.description}")
            emptyList<PostResponse>()
        } catch (e: ClientRequestException) {
            // 4xx responses
            println("Error: ${e.response.status.description}")
            emptyList<PostResponse>()
        } catch (e: ServerResponseException) {
            // 5xx responses
            println("Error: ${e.response.status.description}")
            emptyList<PostResponse>()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList<PostResponse>()
        }
    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse? {
        return try {
            client.post<PostResponse> {
                url(HttpRoutes.POSTS)
                contentType(ContentType.Application.Json)
                body = postRequest
            }
        } catch (e: RedirectResponseException) {
            // 3xx responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            // 4xx responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            // 5xx responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }

}