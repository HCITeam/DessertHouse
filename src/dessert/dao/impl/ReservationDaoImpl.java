package dessert.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dessert.dao.ReservationDao;
import dessert.entity.Reservation;

@Repository
@Transactional
public class ReservationDaoImpl extends BaseDaoImpl<Reservation> implements ReservationDao{

}
