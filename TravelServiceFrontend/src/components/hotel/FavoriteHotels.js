import React from 'react';

import { Box, Button, Card, Grid,  Typography } from '@mui/material';

import { hotelSearchResults } from '../../util/searchResults';

import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';

const FavoriteHotels = (props) => {


  return <Grid item xs={12} sm container sx={{ marginLeft: "20px", marginBottom: "10px" }}>
    {hotelSearchResults.result.slice(0, 3).map(result => (<Grid item xs={12} sm={4} spacing={3}><Card sx={{ maxWidth: 345, marginBottom: "10px" }}>
      <CardMedia
        component="img"
        height="160"
        image={result.max_photo_url}

      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {result.hotel_name}
        </Typography>
        <Typography gutterBottom variant="body2" color="text.primary">
          {result.city_name_en},{result.country_trans}
        </Typography>
        <Typography variant="body2" color="text.primary">
          {result.distances[0].text}
        </Typography>


        <Typography gutterBottom variant="body2" color="text.secondary">
          <Button aria-label="delete" size="small" color="primary" variant="contained" sx={{ marginTop: "15px" }}>
            {result.review_score}
          </Button>

        </Typography>

        <Typography variant="body2" gutterBottom>{result.composite_price_breakdown.all_inclusive_amount.currency} {result.composite_price_breakdown.all_inclusive_amount.value}</Typography>
      </CardContent>
      <CardActions>
        <Button size="small" color="success" >View</Button>

      </CardActions>
    </Card></Grid>)

    )
    }

  </Grid>

}

export default FavoriteHotels;