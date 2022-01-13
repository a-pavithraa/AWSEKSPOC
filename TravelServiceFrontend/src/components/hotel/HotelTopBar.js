import React from 'react';
import { useTheme } from '@mui/material/styles';
import Box from '@mui/material/Box';

import Typography from '@mui/material/Typography';

import CardContent from '@mui/material/CardContent';

import { styled } from '@mui/material/styles';
import { StyledCard } from '../ui/Themes';

import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import { pink } from '@mui/material/colors';

const HotelTopBar = (props) => {    
    
  
   
    return (<StyledCard >
      <Box sx={{ display: 'flex'}}>
        <CardContent sx={{ flex: '1 0 auto' }}>
          <Typography component="div" variant="h6">
            {props.name}
          </Typography>
       
  
        </CardContent>
    <CardContent><span className='rightAlign'> <FavoriteBorderIcon fontSize="large" sx={{ color: pink[500] }}/></span>  </CardContent>
        </Box>
        </StyledCard>);
}

export default HotelTopBar;