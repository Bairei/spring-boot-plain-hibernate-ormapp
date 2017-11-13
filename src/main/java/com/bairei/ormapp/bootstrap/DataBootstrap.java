package com.bairei.ormapp.bootstrap;


import com.bairei.ormapp.models.*;
import com.bairei.ormapp.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class DataBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final BandRepository bandRepository;
    private final AlbumRepository albumRepository;
    private final MemberRepository memberRepository;
    private final LabelRepository labelRepository;
    private final GenreRepository genreRepository;
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final PromoterRepository promoterRepository;
    private final StudioRepository studioRepository;
    private final VenueRepository venueRepository;

    private final static Logger log = LoggerFactory.getLogger(DataBootstrap.class);

    @Autowired
    public DataBootstrap(BandRepository bandRepository, AlbumRepository albumRepository,
                         MemberRepository memberRepository, LabelRepository labelRepository,
                         GenreRepository genreRepository, EventRepository eventRepository,
                         LocationRepository locationRepository, PromoterRepository promoterRepository,
                         StudioRepository studioRepository, VenueRepository venueRepository){
        this.bandRepository = bandRepository;
        this.albumRepository = albumRepository;
        this.memberRepository = memberRepository;
        this.labelRepository = labelRepository;
        this.genreRepository = genreRepository;
        this.locationRepository = locationRepository;
        this.eventRepository = eventRepository;
        this.promoterRepository = promoterRepository;
        this.studioRepository = studioRepository;
        this.venueRepository = venueRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        if(bandRepository.count().equals(0)  && memberRepository.count().equals(0) && albumRepository.count().equals(0)
                && labelRepository.count().equals(0) && genreRepository.count().equals(0)
                && locationRepository.count().equals(0) && eventRepository.count().equals(0)
                && promoterRepository.count().equals(0) && studioRepository.count().equals(0)
                && venueRepository.count().equals(0)){
            initData();
        }
        log.info(bandRepository.count() + " " + memberRepository.count() + " " + albumRepository.count()
                + " " + labelRepository.count() + " " + genreRepository.count() + " " + locationRepository.count()
                + " " + eventRepository.count() + " " + promoterRepository.count() + " " + studioRepository.count()
                + " " + venueRepository.count());
    }

    private void initData(){
        Label defJamRec = new Label();
        defJamRec.setName("Def Jam Recordings");
        defJamRec.setYearFounded(1983);

        Label amRecordings = new Label();
        amRecordings.setName("American Recordings");
        amRecordings.setYearFounded(1988);

        Label nuclearBlast = new Label();
        nuclearBlast.setName("Nuclear Blast");
        nuclearBlast.setYearFounded(1987);

        Genre thrash = new Genre("Thrash Metal");

        labelRepository.save(defJamRec);
        labelRepository.save(amRecordings);
        labelRepository.save(nuclearBlast);
        genreRepository.save(thrash);

        Band slayer = new Band();
        slayer.setName("Slayer");
        Member jh = new Member();
        jh.setName("Jeff Hanneman");
        Member kk = new Member();
        kk.setName("Kerry King");
        Member ta = new Member();
        ta.setName("Tom Araya");
        Member dl = new Member();
        dl.setName("Dave Lombardo");
        Member gh = new Member();
        gh.setName("Gary Holt");
        Member pb = new Member();
        pb.setName("Paul Bostaph");

        slayer.setMembers(new HashSet<>(Arrays.asList(gh,kk,ta,pb)));
        slayer.setGenre(thrash);
        slayer.setYearFounded(1981);

        Location location = new Location();
        location.setPlace("Warsaw, Poland");
        locationRepository.save(location);

        Studio studio = new Studio();
        studio.setLocation(location);
        studio.setName("Some studio name");
        studioRepository.save(studio);

        Album rib = new Album();
        rib.setBand(slayer);
        rib.setGenre(thrash);
        rib.setYearOfRelease(1986);
        rib.setMembers(new HashSet<>(Arrays.asList(jh,kk,ta,dl)));
        rib.setTitle("Reign in Blood");
        rib.setLabel(defJamRec);
        rib.setStudios(new HashSet<>(Arrays.asList(studio)));

        Album sita = new Album();
        sita.setBand(slayer);
        sita.setGenre(thrash);
        sita.setYearOfRelease(1990);
        sita.setMembers(new HashSet<>(Arrays.asList(jh,kk,ta,dl)));
        sita.setTitle("Seasons In The Abyss");
        sita.setLabel(amRecordings);
        sita.setStudios(new HashSet<>(Arrays.asList(studio)));

        Album repentless = new Album();
        repentless.setTitle("Repentless");
        repentless.setBand(slayer);
        repentless.setGenre(thrash);
        repentless.setYearOfRelease(2015);
        repentless.setMembers(new HashSet<>(Arrays.asList(kk,ta,gh,pb)));
        repentless.setLabel(nuclearBlast);
        repentless.setStudios(new HashSet<>(Arrays.asList(studio)));

//        albumRepository.save(Arrays.asList(rib, sita, repentless));
        albumRepository.save(rib);
        albumRepository.save(sita);
        albumRepository.save(repentless);

        slayer.setAlbums(new HashSet<>(Arrays.asList(rib, sita, repentless)));

//        memberRepository.save(Arrays.asList(jh,kk, ta, dl, gh, pb));
        memberRepository.save(jh);
        memberRepository.save(kk);
        memberRepository.save(ta);
        memberRepository.save(dl);
        memberRepository.save(gh);
        memberRepository.save(pb);
        bandRepository.save(slayer);

        Promoter promoter = new Promoter();
        promoter.setName("Live Nation");
        promoter.setYearFounded(1980);
        promoterRepository.save(promoter);



        Venue venue = new Venue();
        venue.setName("Stodola");
        venue.setLocation(location);
        venueRepository.save(venue);

        Event event = new Event();
        event.setEventDate(LocalDate.now());
        event.setType(EventType.CONCERT);
        event.setPromoter(promoter);
        event.setVenue(venue);
        event.setBandSet(new HashSet<>(Arrays.asList(slayer)));
        eventRepository.save(event);

        System.out.println(slayer.getId() + ", members size: " + slayer.getMembers().size());
        for (Member m: slayer.getMembers()) {
            System.out.println("ID: " + m.getId() + ", name: " + m.getName());
        }
        System.out.println("albums size: " + slayer.getAlbums().size());
        for (Album album: slayer.getAlbums()){
            System.out.println("ID: " + album.getId() + ", name: " + album.getTitle());
        }
    }

}
