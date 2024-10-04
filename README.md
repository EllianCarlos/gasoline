# Gasoline

A Homemade test framework for native kotlin, multiplatform by default.


## Running

To build the application from scratch just do:
```
nix build --impure
```
The impure flag is a tech debt of skill issues in nix, will fix it.


If you want to make development changes on an interactive terminal:
```
nix develop
```

## Troubleshooting

### Assertions never fails

This package uses assertions from `kotlin.test`, to enable this you need to run the application with the `-ea` in the JVM options.
