package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

    private TripService tripService;


    @Test
    void throwsAnException() {
        User loggedUser = null;
        tripService = new MyTripService(loggedUser);
        User user = new User();
        assertThrows(UserNotLoggedInException.class, () -> {
            tripService.getTripsByUser(user);
        });
    }

    @Test
    void returnEmptyTripList_whenUserHasNoFriend() {
        // Given
        User loggedUser = new User();
        tripService = new MyTripService(loggedUser);
        User userWithNoFriends = new User();
        // When
        List<Trip> trips = tripService.getTripsByUser(userWithNoFriends);
        // Then
        assertThat(trips).isEmpty();
    }

    @Test
    void returnTripListOfUser_whenUserHasAFriendWhichIsLoggedUser() {
        // Given
        User loggedUser = new User();
        Trip trip = new Trip();
        tripService = new MyTripService(loggedUser, singletonList(trip));
        User user = new User();
        user.addFriend(loggedUser);
        // When
        List<Trip> trips = tripService.getTripsByUser(user);
        // Then
        assertThat(trips).containsExactly(trip);
    }


    private static class MyTripService extends TripService {
        private final User loggedUser;
        private final List<Trip> userTrips;

        public MyTripService(User loggedUser, List<Trip> userTrips) {
            this.loggedUser = loggedUser;
            this.userTrips = userTrips;
        }

        public MyTripService(User loggedUser) {
            this(loggedUser, emptyList());
        }

        @Override
        User getLoggedUser() {
            return loggedUser;
        }

        @Override
        List<Trip> getUserTrips(User user) {
            return userTrips;
        }

    }

}
