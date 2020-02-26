package com.onur.spring.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Auditorium {

    private String name;

    private long numberOfSeats;

    private Set<Long> vipSeats;

    public Auditorium(){

    }

    public Auditorium(String name, long numberOfSeats, String vipSeatsList) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.vipSeats = (new ArrayList<String>(Arrays.asList(vipSeatsList.split(",")))).stream().map(Long::valueOf).collect(Collectors.toSet());
    }

    /**
     * Counts how many vip seats are there in supplied <code>seats</code>
     *
     * @param seats
     *            Seats to process
     * @return number of vip seats in request
     */
    public long countVipSeats(Collection<Long> seats) {
        return seats.stream().filter(seat -> vipSeats.contains(seat)).count();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(long numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Set<Long> getAllSeats() {
        return LongStream.range(1, numberOfSeats+1).boxed().collect(Collectors.toSet());
    }

    public Set<Long> getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(Set<Long> vipSeats) {
        this.vipSeats = vipSeats;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Auditorium other = (Auditorium) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Auditorium{" +
                "name='" + name + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", vipSeats=" + vipSeats +
                '}';
    }
}
