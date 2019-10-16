package com.github.tamurashingo.salonservice.domain.service;


/**
 * サロンの統計情報を取得するサービス
 */
public interface StaticsService {
    int countUsers();
    int countSalons();
}
