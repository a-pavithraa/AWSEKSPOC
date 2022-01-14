import React, { useState,useContext } from "react";

import AppBar from '@mui/material/AppBar';
import Button from '@mui/material/Button';
import { Toolbar, Typography } from "@mui/material";
import moduleClasses from './NavBar.module.scss';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import FlightIcon from '@mui/icons-material/Flight';

import HotelIcon from '@mui/icons-material/Hotel';
import { 
    Link
   
  } from 'react-router-dom';
import AuthContext from "../../store/auth-context";
const NavBar = () => {
    const [value, setValue] = React.useState(0);
    const authCtx=useContext(AuthContext);

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };
    let appBarContent = <Typography component="div" variant="h5">TRAVEL POC</Typography>
    
    if(authCtx.isLoggedIn)
    appBarContent= <Tabs
    value={value}
    onChange={handleChange}
    aria-label="icon position tabs example"
>
    <Tab sx={{color:"white"}} icon={<HotelIcon sx={{ color: "white", fontSize: 30 }} />}   label="HOTEL" to="/hotelBookings" component={Link}/>
    <Tab sx={{color:"white"}} icon={<FlightIcon sx={{ color: "white" }} />}   label="FLIGHT" to="/flightBookings" component={Link}/>
 

</Tabs>
             
        
  
    return (
        <div >
            <AppBar position="static" sx={{
                fontWeight: "bolder",
                width: '100%',
                fontSize: '20px',
                marginBottom: `20px`
            }}>
              
                <Toolbar>

                   {appBarContent}
                    <div className={moduleClasses.rightAlignment}>
                        
                      
                     {authCtx.isLoggedIn &&   <Button color="inherit" onClick={authCtx.logout} >Logout</Button>}
                    </div>
                </Toolbar>
            </AppBar>
        </div>
    );
}

export default NavBar;