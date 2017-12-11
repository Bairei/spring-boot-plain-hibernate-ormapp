package com.bairei.ormapp.services.implementation;

import com.bairei.ormapp.models.Album;
import com.bairei.ormapp.models.Band;
import com.bairei.ormapp.models.Member;
import com.bairei.ormapp.repositories.MemberRepository;
import com.bairei.ormapp.services.AlbumService;
import com.bairei.ormapp.services.BandService;
import com.bairei.ormapp.services.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final BandService bandService;
    private final AlbumService albumService;

    public MemberServiceImpl(MemberRepository memberRepository, BandService bandService, AlbumService albumService) {
        this.memberRepository = memberRepository;
        this.bandService = bandService;
        this.albumService = albumService;
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public void saveOrUpdate(Member member) {
        memberRepository.saveOrUpdate(member);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long aLong) {
        Member member = findById(aLong);
        if (member != null){
            List<Band> bands = bandService.findBandsByMembersIncludingMemberNameEqualsIgnoreCase(member.getName());
            for(Band band : bands){
                Set<Member> memberSet = band.getMembers();
                memberSet.remove(member);
                log.info(String.valueOf(band.getMembers().size()));
                band.setMembers(memberSet);
                log.info(String.valueOf(band.getMembers().size()));
                bandService.save(band);
            }
            List<Album> albums = albumService.findAlbumsByMembersIncludingMemberNameEqualsIgnoreCase(member.getName());
            for (Album album: albums){
                Set<Member> memberSet = album.getMembers();
                memberSet.remove(member);
                album.setMembers(memberSet);
                albumService.save(album);
            }
            memberRepository.deleteById(aLong);
        }
    }

    @Override
    public Integer count() {
        return memberRepository.count();
    }
}
