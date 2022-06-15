package cs350s22.component.ui.parser;

import cs350s22.component.sensor.A_Sensor;
import cs350s22.component.sensor.mapper.A_Mapper;
import cs350s22.component.sensor.reporter.A_Reporter;
import cs350s22.component.sensor.watchdog.A_Watchdog;
import cs350s22.support.Identifier;

import java.util.List;

public class MySensor extends A_Sensor {
    public MySensor(Identifier id, List<A_Reporter> reporters, List<A_Watchdog> watchdogs) {
        super(id, reporters, watchdogs);
    }
    public MySensor(Identifier id, List<A_Reporter> reporters, List<A_Watchdog> watchdogs, A_Mapper mapper){
        super(id, reporters, watchdogs, mapper);
    }
    public MySensor(Identifier id, List<A_Reporter> reporters, List<A_Watchdog> watchdogs, List<Identifier> groups){
        super(id, reporters, watchdogs, groups);
    }
    public MySensor(Identifier id, List<A_Reporter> reporters, List<A_Watchdog> watchdogs, List<Identifier> groups, A_Mapper mapper){
        super(id, reporters, watchdogs, groups, mapper);
    }
}
