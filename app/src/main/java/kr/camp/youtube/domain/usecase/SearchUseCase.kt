package kr.camp.youtube.domain.usecase

import kr.camp.youtube.domain.repository.SearchRepository
import kr.camp.youtube.domain.mapper.toEntity
import kr.camp.youtube.domain.model.SearchEntity

class SearchUseCase(
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(query: String): Result<SearchEntity> {
        return runCatching {
            searchRepository.getSearch(query).toEntity()
        }
    }
}