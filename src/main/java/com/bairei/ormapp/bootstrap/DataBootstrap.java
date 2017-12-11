package com.bairei.ormapp.bootstrap;


import com.bairei.ormapp.models.*;
import com.bairei.ormapp.services.*;
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

    private final BandService bandService;
    private final AlbumService albumService;
    private final MemberService memberService;
    private final LabelService labelService;
    private final GenreService genreService;
    private final EventService eventService;
    private final LocationService locationService;
    private final PromoterService promoterService;
    private final StudioService studioService;
    private final VenueService venueService;

    private final static Logger log = LoggerFactory.getLogger(DataBootstrap.class);

    @Autowired
    public DataBootstrap(BandService bandService, AlbumService albumService,
                         MemberService memberService, LabelService labelService,
                         GenreService genreService, EventService eventService,
                         LocationService locationService, PromoterService promoterService,
                         StudioService studioService, VenueService venueService){
        this.bandService = bandService;
        this.albumService = albumService;
        this.memberService = memberService;
        this.labelService = labelService;
        this.genreService = genreService;
        this.locationService = locationService;
        this.eventService = eventService;
        this.promoterService = promoterService;
        this.studioService = studioService;
        this.venueService = venueService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        if(bandService.count().equals(0)  && memberService.count().equals(0) && albumService.count().equals(0)
                && labelService.count().equals(0) && genreService.count().equals(0)
                && locationService.count().equals(0) && eventService.count().equals(0)
                && promoterService.count().equals(0) && studioService.count().equals(0)
                && venueService.count().equals(0)){
            initData();
        }
        log.info("bands by name including ayer: " + String.valueOf(bandService.findBandsByNameIncluding("ayer").size()));
        log.info("albums by title including s in the ab: " + String.valueOf(albumService.findAlbumsByTitleIncluding("s in the ab").size()));
        log.info("bands by genre name equals thrash Metal (ignoring case): " + String.valueOf(bandService.findBandsByGenreNameEqualsIgnoreCase("thrash Metal")));
        log.info("albums by genre name equals thrash Metal (ignoring case): " + String.valueOf(albumService.findAlbumsByGenreNameEqualsIgnoreCase("thrash Metal").size()));
        log.info("albums by members including name Jeff Hanneman: " + String.valueOf(albumService.findAlbumsByMembersIncludingMemberNameEqualsIgnoreCase("jeff haNNeman")));
        log.info("bands by members including name Jeff Hanneman: " + String.valueOf(bandService.findBandsByMembersIncludingMemberNameEqualsIgnoreCase("jeff hanneman")));
        log.info("bands by members including name Tom Araya: " + String.valueOf(bandService.findBandsByMembersIncludingMemberNameEqualsIgnoreCase("TOM araYa")));
        log.info("events by bands including band name slayer:" + eventService.findEventsByBandsIncludingBandNameEqualsIgnoreCase("slayer"));
        log.info("studios by location place like warsaw: " + studioService.findStudiosByLocationPlaceIncluding("warsaw"));
        log.info("venues by location place like warsaw: " + venueService.listVenuesByLocationPlaceIncluding("warsaw").toString());
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

        labelService.save(defJamRec);
        labelService.save(amRecordings);
        labelService.save(nuclearBlast);
        genreService.save(thrash);

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
        locationService.save(location);

        Studio studio = new Studio();
        studio.setLocation(location);
        studio.setName("Some studio name");
        studioService.save(studio);

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

//        albumService.save(Arrays.asList(rib, sita, repentless));
        albumService.save(rib);
        albumService.save(sita);
        albumService.save(repentless);

        slayer.setAlbums(new HashSet<>(Arrays.asList(rib, sita, repentless)));

//        memberService.save(Arrays.asList(jh,kk, ta, dl, gh, pb));
        memberService.save(jh);
        memberService.save(kk);
        memberService.save(ta);
        memberService.save(dl);
        memberService.save(gh);
        memberService.save(pb);
        bandService.save(slayer);

        Promoter promoter = new Promoter();
        promoter.setName("Live Nation");
        promoter.setYearFounded(1980);
        promoterService.save(promoter);



        Venue venue = new Venue();
        venue.setName("Stodola");
        venue.setLocation(location);
        venueService.save(venue);

        Event event = new Event();
        event.setEventDate(LocalDate.now());
        event.setType(EventType.CONCERT);
        event.setPromoter(promoter);
        event.setVenue(venue);
        event.setBandSet(new HashSet<>(Arrays.asList(slayer)));
        eventService.save(event);

        System.out.println(slayer.getId() + ", members size: " + slayer.getMembers().size());
        for (Member m: slayer.getMembers()) {
            System.out.println("ID: " + m.getId() + ", name: " + m.getName());
        }
        System.out.println("albums size: " + slayer.getAlbums().size());
        for (Album album: slayer.getAlbums()) {
            System.out.println("ID: " + album.getId() + ", name: " + album.getTitle());
        }
    }

}
