package com.fivengers.blooming.artist.application;

import com.fivengers.blooming.artist.application.port.in.ArtistApplicationUseCase;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistApplyRequest;
import com.fivengers.blooming.artist.application.port.out.ArtistApplicationPort;
import com.fivengers.blooming.artist.domain.ArtistApplication;
import com.fivengers.blooming.artist.domain.ArtistApplicationState;
import com.fivengers.blooming.global.exception.member.MemberNotFoundException;
import com.fivengers.blooming.member.application.port.out.MemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistApplicationService implements ArtistApplicationUseCase {

    private final MemberPort memberPort;
    private final ArtistApplicationPort artistApplicationPort;

    @Override
    public Page<ArtistApplication> searchByArtistApplicationState(Pageable pageable, ArtistApplicationState state) {
        return artistApplicationPort.findByArtistApplicationState(pageable, state);
    }

    @Override
    public ArtistApplication add(ArtistApplyRequest request, Long memberId) {
        return artistApplicationPort.save(request.toDomain(
                memberPort.findById(memberId).orElseThrow(MemberNotFoundException::new)));
    }
}
