package com.fivengers.blooming.membership.adapter.out.persistence.mapper;

import com.fivengers.blooming.artist.adapter.out.persistence.mapper.ArtistMapper;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.NftSaleJpaEntity;
import com.fivengers.blooming.membership.domain.NftSale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NftSaleMapper {

    private final MembershipMapper membershipMapper;

    public NftSale toDomain(NftSaleJpaEntity nftSaleJpaEntity) {
        return NftSale.builder()
                .id(nftSaleJpaEntity.getId())
                .totalNftCount(nftSaleJpaEntity.getTotalNftCount())
                .soldNftCount(nftSaleJpaEntity.getSoldNftCount())
                .totalNftAmount(nftSaleJpaEntity.getTotalNftAmount())
                .soldNftAmount(nftSaleJpaEntity.getSoldNftAmount())
                .membership(membershipMapper.toDomain(nftSaleJpaEntity.getMembershipJpaEntity()))
                .build();
    }

    public NftSaleJpaEntity toJpaEntity(NftSale nftSale) {
        return NftSaleJpaEntity.builder()
                .totalNftCount(nftSale.getTotalNftCount())
                .soldNftCount(nftSale.getSoldNftCount())
                .totalNftAmount(nftSale.getTotalNftAmount())
                .soldNftAmount(nftSale.getSoldNftAmount())
                .membershipJpaEntity(membershipMapper.toJpaEntity(nftSale.getMembership()))
                .deleted(false)
                .build();
    }
}
