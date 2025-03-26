package com.seunghyun.portfolio.admin.security

import com.seunghyun.portfolio.admin.exception.AdminBadRequestException
import com.seunghyun.portfolio.domain.repository.AccountRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class SecurityService(
    private val accountRepository: AccountRepository
) : UserDetailsService {

    override fun loadUserByUsername(loginId: String): UserDetails {
        return accountRepository.findByLoginId(loginId)
            .orElseThrow { throw AdminBadRequestException("사용자 정보를 찾을 수 없습니다.") }
    }

}