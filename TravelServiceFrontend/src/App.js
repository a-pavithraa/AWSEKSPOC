
import './App.scss';
import Layout from './components/ui/Layout';
import FlightBooking from './pages/FlightBooking';
import HotelBooking from './pages/HotelBooking';
import HotelDetails from './pages/HotelDetails';
import { Route, Routes, Navigate, Link } from 'react-router-dom';
import FavoriteHotels from './components/hotel/FavoriteHotels';
import RequireAuth from './util/RequireAuth';

import Login from './pages/Login';

Number.prototype.format = function(n, x) {
  var re = '\\d(?=(\\d{' + (x || 3) + '})+' + (n > 0 ? '\\.' : '$') + ')';
  return this.toFixed(Math.max(0, ~~n)).replace(new RegExp(re, 'g'), '$&,');
};

function App() {
  
  return (
    <div className="App">
       <header className="App-header">
         <Layout>
         <Routes>
           
        <Route path='/login' element={<Login/>} />
        <Route element={<RequireAuth />}>
          <Route path="/" element={<HotelBooking />} />
        <Route path='/flightBookings' element={<FlightBooking/>}/>
        <Route path='/hotelBookings' element={<HotelBooking />} />
        <Route path='/favHotels' element={<FavoriteHotels />} />
        <Route path='/hotelDetails/:hotelId/:hotelName' element={<HotelDetails />} />
          </Route>
      
        </Routes>
         </Layout>
       </header>
     
    </div>
  );
}

export default App;
