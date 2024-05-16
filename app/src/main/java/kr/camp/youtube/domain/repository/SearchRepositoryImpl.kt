package kr.camp.youtube.domain.repository

import kr.camp.youtube.data.model.SearchResponse
import kr.camp.youtube.data.remote.SearchDataSource
import kr.camp.youtube.data.repository.SearchRepository
import kr.camp.youtube.domain.exception.NetworkException
import kr.camp.youtube.domain.exception.QuotaExceededException
import kr.camp.youtube.domain.exception.TimeoutException
import kr.camp.youtube.domain.exception.UnknownException
import kr.camp.youtube.domain.exception.UnknownHttpException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class SearchRepositoryImpl(
    private val searchDataSource: SearchDataSource
) : SearchRepository {

    override suspend fun getSearch(query: String): SearchResponse {
        return try {
            searchDataSource.getSearch(query)
        } catch (e: HttpException) {
            e.printStackTrace()
            val message = e.message
            throw when (val code = e.code()) {
                403 -> QuotaExceededException(message)
                else -> UnknownHttpException(code, message)
            }
        } catch (e: SocketTimeoutException) {
            e.printStackTrace()
            throw TimeoutException(e.message)
        } catch (e: UnknownHostException) {
            e.printStackTrace()
            throw NetworkException(e.message)
        } catch (e: Exception) {
            e.printStackTrace()
            throw UnknownException(e.message)
        }
    }
}