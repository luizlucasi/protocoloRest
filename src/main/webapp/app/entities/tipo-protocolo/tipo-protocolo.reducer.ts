import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITipoProtocolo, defaultValue } from 'app/shared/model/tipo-protocolo.model';

export const ACTION_TYPES = {
  FETCH_TIPOPROTOCOLO_LIST: 'tipoProtocolo/FETCH_TIPOPROTOCOLO_LIST',
  FETCH_TIPOPROTOCOLO: 'tipoProtocolo/FETCH_TIPOPROTOCOLO',
  CREATE_TIPOPROTOCOLO: 'tipoProtocolo/CREATE_TIPOPROTOCOLO',
  UPDATE_TIPOPROTOCOLO: 'tipoProtocolo/UPDATE_TIPOPROTOCOLO',
  DELETE_TIPOPROTOCOLO: 'tipoProtocolo/DELETE_TIPOPROTOCOLO',
  RESET: 'tipoProtocolo/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITipoProtocolo>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TipoProtocoloState = Readonly<typeof initialState>;

// Reducer

export default (state: TipoProtocoloState = initialState, action): TipoProtocoloState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TIPOPROTOCOLO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TIPOPROTOCOLO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TIPOPROTOCOLO):
    case REQUEST(ACTION_TYPES.UPDATE_TIPOPROTOCOLO):
    case REQUEST(ACTION_TYPES.DELETE_TIPOPROTOCOLO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TIPOPROTOCOLO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TIPOPROTOCOLO):
    case FAILURE(ACTION_TYPES.CREATE_TIPOPROTOCOLO):
    case FAILURE(ACTION_TYPES.UPDATE_TIPOPROTOCOLO):
    case FAILURE(ACTION_TYPES.DELETE_TIPOPROTOCOLO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TIPOPROTOCOLO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TIPOPROTOCOLO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TIPOPROTOCOLO):
    case SUCCESS(ACTION_TYPES.UPDATE_TIPOPROTOCOLO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TIPOPROTOCOLO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/tipo-protocolos';

// Actions

export const getEntities: ICrudGetAllAction<ITipoProtocolo> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TIPOPROTOCOLO_LIST,
  payload: axios.get<ITipoProtocolo>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITipoProtocolo> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TIPOPROTOCOLO,
    payload: axios.get<ITipoProtocolo>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITipoProtocolo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TIPOPROTOCOLO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITipoProtocolo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TIPOPROTOCOLO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITipoProtocolo> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TIPOPROTOCOLO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
