# soccer-win-counter
Manage you wins/losses against your buddies on soccer games 

## API definition
### GET /teams
```json
[{
  "name": "Arsenal",
  "league": "Premiere league",
  "image": "<base64image>" 
}]
```

### GET /players
```json
[{
  "name": "Robson"
}]
```

### GET /match-results
```json
[{
  "home_team": "<team_name>",
  "home_team_goals": 1,
  "home_team_player": "<player_uid>",
  "away_team": "<team_name>",
  "away_team_goals": 2,
  "home_team_player": "<player_uid>",
  "date_time": "2016-10-01 00:00:00"
}]
```

